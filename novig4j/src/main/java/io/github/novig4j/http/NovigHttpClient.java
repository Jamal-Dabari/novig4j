package io.github.novig4j.http;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.novig4j.JacksonConfig;

import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NovigHttpClient implements AutoCloseable {
    private final HttpClient client;
    private final NovigEnvironment environment;
    private final NovigCredentials credentials;
    private final TokenManager tokenManager;
    private final ObjectMapper mapper;


    private NovigHttpClient(Builder b) {
        environment = b.environment;
        credentials = b.credentials;
        this.client = HttpClient.newBuilder().build();
        this.tokenManager = new TokenManager(credentials, environment, client);
        this.mapper = JacksonConfig.create();
    }

    // in-case user want to pass their own client in
    private NovigHttpClient(Builder b, HttpClient client) {
        environment = b.environment;
        credentials = b.credentials;
        this.client = client;
        this.tokenManager = new TokenManager(credentials, environment, client);
        this.mapper = JacksonConfig.create();
    }


    @Override
    public void close() throws InterruptedException {
        client.close();
    }

    public String clientId() {
        return credentials.client_id();
    }
    public NovigEnvironment getEnvironment() {return environment;}

    public static final class Builder {
        private NovigCredentials credentials;
        private NovigEnvironment environment;

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



        public NovigHttpClient build() {

            if (credentials == null) {
                throw new IllegalStateException("Please Provide Credentials");
            }


            if (environment == null) {
                throw new IllegalStateException("Please Provide environment");
            }

            return new NovigHttpClient(this);
        }


    }


}
