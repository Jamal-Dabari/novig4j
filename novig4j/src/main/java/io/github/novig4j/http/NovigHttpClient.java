package io.github.novig4j.http;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.time.Clock;
import java.util.concurrent.CompletableFuture;

public class NovigHttpClient implements AutoCloseable {
    private final HttpClient client;
    private final NovigEnvironment environment;
    private final NovigCredentials credentials;
    private final TokenManager tokenManager;
    private final ObjectMapper mapper;
    private final RequestFactory requests;
    private final Clock timer;


    private NovigHttpClient(Builder b) {
        environment = b.environment;
        credentials = b.credentials;
        this.client = b.client;
        this.mapper = b.mapper;
        this.timer = Clock.systemUTC();
        this.tokenManager = new TokenManager(credentials, environment, mapper, timer);
        this.requests = new RequestFactory(environment.restUrl());
    }



    @Override
    public void close()  {
        client.close();
    }
    public String clientId() {
        return credentials.clientId();
    }
    public NovigEnvironment getEnvironment() {return environment;}
    public HttpClient client() {return client;}
    public ObjectMapper getMapper(){return mapper;}


    public Response sendRequest(Request r) throws IOException, InterruptedException {
        client.send(requests.toHttpRequest(r, tokenManager.get().value()), HttpResponse.BodyHandlers.ofString());
        return null;
    }

    public Response sendAsyncRequest(Request r) throws IOException, InterruptedException {
        return client.sendAsync(requests.toHttpRequest(r, tokenManager.get().value()), HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(body -> {
                    try {
                        return mapper.readValue(body, Response.class);
                    } catch (IOException e){
                        throw new UncheckedIOException(e);
                    }
                }).join();
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private NovigCredentials credentials;
        private NovigEnvironment environment;
        private HttpClient client = HttpClient.newBuilder().build();
        private ObjectMapper mapper = JacksonConfig.create();

        public Builder() {

        }

        public Builder credentials(NovigCredentials credentials) {
            this.credentials = credentials;
            return this;
        }


        public Builder environment(NovigEnvironment environment) {
            this.environment = environment;
            return this;
        }

        public Builder client(HttpClient client){
            this.client = client;
            return this;
        }

        public Builder mapper(ObjectMapper mapper){
            this.mapper = mapper;
            return this;
        }



        public NovigHttpClient build() {


            if (environment == null){
                throw new IllegalArgumentException("Environment is required");
            }

            if (credentials == null){
                throw new IllegalArgumentException("credentials are required");
            }

            return new NovigHttpClient(this);
        }


    }


}
