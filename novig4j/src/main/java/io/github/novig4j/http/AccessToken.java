package io.github.novig4j.http;


import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public record AccessToken(String value, Instant expiresAt) {

    public static final Duration margin = Duration.ofSeconds(60);

    public AccessToken {
        Objects.requireNonNull(value);
        Objects.requireNonNull(expiresAt);
    }


    public static AccessToken from(AccessTokenResponse tokenResponse, Clock clock){
        Instant expiration = tokenResponse.expiresIn() == null
                ? Instant.MAX
                : clock.instant().plusSeconds(tokenResponse.expiresIn());

        return new AccessToken(tokenResponse.accessToken(), expiration);
    }

    boolean isValid(Clock clock){
        return clock.instant().plus(margin).isBefore(expiresAt);

    }

    @Override
    public String toString() {
        return "AccessToken Expires at " + expiresAt;
    }

}
