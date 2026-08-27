package model.Markets;

public record OrderBook(String marketId, String marketDescription, OutcomeLadders[] outcomeLadders) {
}
