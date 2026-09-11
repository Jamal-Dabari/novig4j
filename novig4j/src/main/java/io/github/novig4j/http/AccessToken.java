package io.github.novig4j.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AccessToken(String value, @JsonProperty("expires_in") Instant expiresAt) {

    public AccessToken {
        Objects.requireNonNull(value);
        Objects.requireNonNull(expiresAt);

    }

    boolean isValid(Instant now, Duration margin){
        return now.plus(margin).isBefore(expiresAt);

    }
    @Override
    public String toString() {
        return "AccessToken Expires at " + expiresAt;
    }

}
