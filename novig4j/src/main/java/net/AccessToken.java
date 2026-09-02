package net;

import java.time.Instant;

public record AccessToken(String value, Instant expiresAt) {

    @Override

    @Override
    public String toString() {
        return "PlaceHolder";
    }
}
