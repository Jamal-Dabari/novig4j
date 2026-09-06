package io.github.novig4j.http;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpClient;

public class TokenManager {
    private NovigCredentials credentials;
    private NovigEnvironment environment;
    private HttpClient client;
    private final ObjectMapper mapper;

    public TokenManager(NovigCredentials credentials, NovigEnvironment environment, HttpClient client, ObjectMapper mapper){
        this.credentials = credentials;
        this.environment = environment;
        this.client = client;
        this.mapper = mapper;
    }
}
