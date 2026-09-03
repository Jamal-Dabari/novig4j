package io.github.novig4j.http;

public enum NovigEnvironment {
    PRODUCTION("https://api.novig.us/nbx/v2", "https://api.novig.us/nbx/v1/auth/emm-token", "wss://api.novig.us/tape"),
    QA("https://api-qa.novig.us/nbx/v2", "https://api-qa.novig.us/nbx/v1/auth/emm-token", "wss://api-qa.novig.us/tape");

    private final String restUrl;
    private final String authUrl;
    private final String wsUrl;


    NovigEnvironment(String restUrl, String authUrl, String wsUrl) {
        this.restUrl = restUrl;
        this.authUrl = authUrl;
        this.wsUrl = wsUrl;

    }

    public String getRestUrl() {
        return restUrl;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public String getWsUrl() {
        return wsUrl;
    }
}
