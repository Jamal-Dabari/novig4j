package io.github.novig4j.http;

import java.net.http.HttpClient;

public class TokenManager {
    private NovigCredentials credentials;
    private NovigEnvironment environment;
    private HttpClient client;

    public TokenManager(NovigCredentials credentials, NovigEnvironment environment, HttpClient client){
        this.credentials = credentials;
        this.environment = environment;
        this.client = client;
    }
}
