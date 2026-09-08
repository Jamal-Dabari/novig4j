package io.github.novig4j.http;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Objects;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record NovigCredentials(String clientId, String clientSecret) {

    public NovigCredentials {
        Objects.requireNonNull(clientId, "clientId");
        Objects.requireNonNull(clientSecret,"clientSecret");

        if (clientId.isBlank()) throw new IllegalArgumentException("Client id must not be blank");
        if (clientSecret.isBlank()) throw new IllegalArgumentException("Client Secret must not be blank");
    }

    @Override
    public String toString() {
        return "Credentials[clientId=***"  + ", clientSecret=***]";
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NovigCredentials that = (NovigCredentials) o;
        return Objects.equals(clientId, that.clientId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId);
    }
}
