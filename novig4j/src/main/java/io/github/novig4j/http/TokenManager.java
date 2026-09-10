package io.github.novig4j.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

class TokenManager {
    private final NovigCredentials credentials;
    private final NovigEnvironment environment;
    private final NovigHttpClient client;
    private final ObjectMapper mapper;
    private volatile AccessToken token;
    private final Clock timer;

    public TokenManager(NovigCredentials credentials, NovigEnvironment environment, NovigHttpClient client, ObjectMapper mapper, Clock timer){
        this.credentials = credentials;
        this.environment = environment;
        this.client = client;
        this.mapper = mapper;
        this.timer = timer;
    }

    AccessToken get() throws IOException, InterruptedException {

        if (token == null) {
            Request r = Request.builder().method(HttpMethod.POST).headers("Content-Type: ", "application/json").body(
                    "grant_type: client_credentials, client_id: " + credentials.clientId() + ", client_secret: " + credentials.clientSecret()

            ).build();

            Response response = client.sendAsyncRequest(r);
            if (response.statusCode() == 401){
                invalidate(this.token);
            }


            buildToken(response);

            if (!isValid(timer.instant(), Duration.of(30, ChronoUnit.MINUTES))){
                // reissue the token;
                Response newToken = client.sendAsyncRequest(r);
                buildToken(response);
            }


        }

        return token;
    }

    void buildToken(Response response) throws IOException {
        token = mapper.readValue(response.body(), AccessToken.class);
    }


    boolean isValid(Instant now, Duration margin){
        Instant expiresAt = token.expiresAt();
        return now.plus(margin).isBefore(expiresAt);

    }

    void invalidate(AccessToken token){
        this.token = null;
    }

    public AccessToken fetch(){
        return token;
    }

}
