package net;

public record NovigCredentials(String client_id, String client_secret) {



    @Override
    public String toString() {
        return "HIDING SECRETS";
    }
}
