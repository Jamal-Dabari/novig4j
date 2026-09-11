package io.github.novig4j.http;

import java.net.URI;
import java.util.Objects;

public record NovigEnvironment(URI restUrl, URI authUrl, URI wsUrl) {

    public static final NovigEnvironment PRODUCTION = new NovigEnvironment(URI.create("https://api.novig.us/nbx/v2/"),URI.create("https://api.novig.us/nbx/v1/auth/emm-token/"), URI.create("wss://api.novig.us/tape"));
    public static final NovigEnvironment QA = new NovigEnvironment(URI.create("https://api-qa.novig.us/nbx/v2/"), URI.create("https://api-qa.novig.us/nbx/v1/auth/emm-token/"), URI.create("wss://api-qa.novig.us/tape"));

    public NovigEnvironment{
        Objects.requireNonNull(restUrl);
        Objects.requireNonNull(authUrl);

        if (!restUrl.isAbsolute() || !authUrl.isAbsolute()){
            throw new IllegalArgumentException("endpoints must be absolute");
        }
    }


}


