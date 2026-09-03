package io.github.novig4j.http;

import java.time.Instant;

public record AccessToken(String value, Instant expiresAt) {

    @Override
    public String toString() {
        return "AccessToken Expires at " + expiresAt;
    }

}
