package io.github.novig4j.http;

import java.util.Objects;

public record NovigCredentials(String client_id, String client_secret) {

    public NovigCredentials {
        if (client_id == null || client_id.equals("")) {
            throw new IllegalStateException("Client Id cannot be null or empty");
        }

        if (client_secret == null || client_secret.equals("")) {
            throw new IllegalStateException("Client Secret Cannot be null");
        }
    }


    @Override
    public String toString() {
        return "HIDING SECRETS";
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NovigCredentials that = (NovigCredentials) o;
        return Objects.equals(client_id, that.client_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(client_id);
    }
}
