package model.Markets;

public record Event(String id, String description, String type, Status status, Game game) {
}
