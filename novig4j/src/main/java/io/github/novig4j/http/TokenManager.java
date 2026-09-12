package io.github.novig4j.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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

        if (token != null && token.isValid(timer)){
            return token;
        }

        lock.lock();

        try {
            t = token;

            if (token != null && token.isValid(timer)){
                return token;
            }

            this.token = fetch();

            return token;
        } finally {

            lock.unlock();

        }
    }

    private AccessToken buildToken(HttpResponse<String> response) throws IOException {
        AccessTokenResponse read = mapper.readValue(response.body(), AccessTokenResponse.class);
        AccessToken builtToken = AccessToken.from(read, timer);

        return builtToken;
    }




    private void invalidate(){
        this.token = null;
    }

    private AccessToken fetch() throws IOException, InterruptedException {
        AccessToken cachedToken;

        HttpRequest request = HttpRequest.newBuilder(environment.authUrl())
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(credentials)))
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 300 ){
            cachedToken = buildToken(response);
            return cachedToken;
        } else {
            throw new IOException("Token error with response code: " + response.statusCode());
        }

    }

}
