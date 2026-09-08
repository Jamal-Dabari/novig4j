package io.github.novig4j.http;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.Objects;
import java.net.http.HttpResponse;

public class NovigHttpClient implements AutoCloseable {
    private final HttpClient client;
    private final NovigEnvironment environment;
    private final NovigCredentials credentials;
    private final TokenManager tokenManager;
    private final ObjectMapper mapper;
    private final RequestFactory requests;


    private NovigHttpClient(Builder b) {
        environment = b.environment;
        credentials = b.credentials;
        this.client = b.client;
        this.mapper = b.mapper;
        this.tokenManager = new TokenManager(credentials, environment, client, mapper);
        this.requests = new RequestFactory(environment, tokenManager);
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

    public void sendRequest(Request r) throws IOException, InterruptedException {
        client.send(requests.toHttpRequest(r), HttpResponse.BodyHandlers.ofString());
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


            Objects.requireNonNull(environment, "Environment is required");
            Objects.requireNonNull(credentials, "Credentials are required");

            return new NovigHttpClient(this);
        }


    }


}
