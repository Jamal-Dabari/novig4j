package io.github.novig4j.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.time.Clock;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.locks.ReentrantLock;

class TokenManager {
    private final NovigCredentials credentials;
    private final NovigEnvironment environment;
    private final HttpClient client;
    private final ObjectMapper mapper;
    private volatile AccessToken token;
    private final Clock timer;
    private final ReentrantLock lock;

    public TokenManager(NovigCredentials credentials, NovigEnvironment environment,  ObjectMapper mapper, Clock timer){
        this.credentials = credentials;
        this.environment = environment;
        this.client = HttpClient.newHttpClient();
        this.mapper = mapper;
        this.timer = timer;
        this.lock = new ReentrantLock();
    }

    public AccessToken get() throws IOException, InterruptedException {
        AccessToken t = token;
        Duration refresh = Duration.of(60, ChronoUnit.SECONDS);

        if (token != null && token.isValid(timer.instant(), refresh)){
            return token;
        }

        lock.lock();

        try {
            t = token;

            if (token != null && token.isValid(Clock.systemUTC().instant(), refresh)){
                return token;
            }

            this.token = fetch();

            return token;
        } finally {

            lock.unlock();

        }
    }

    private AccessToken buildToken(Response response) throws IOException {
        AccessToken builtToken = mapper.readValue(response.body(), AccessToken.class);
        return builtToken;
    }




    private void invalidate(){
        this.token = null;
    }

    private AccessToken fetch() throws IOException, InterruptedException {
        AccessToken cachedToken;

        Request r = Request.builder()
                .method(HttpMethod.POST)
                .path("").
                headers("Content-Type: ", "application/json")
                .body(mapper.writeValueAsString(credentials))
                .build();

        // Response response = client.sendAsyncRequest(r);

    //    cachedToken = buildToken(response);

     //   return cachedToken;
        return null;
    }

}
