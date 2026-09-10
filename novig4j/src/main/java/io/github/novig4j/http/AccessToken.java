package io.github.novig4j.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccessToken(String value, Instant expiresAt) {

    public AccessToken {
        Objects.requireNonNull(value);
        Objects.requireNonNull(expiresAt);

    }

    @Override
    public String toString() {
        return "AccessToken Expires at " + expiresAt;
    }

}
