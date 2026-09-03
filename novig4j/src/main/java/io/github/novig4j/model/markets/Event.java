package io.github.novig4j.model.markets;

public record Event(String id, String description, String type, Status status, Game game) {
}
