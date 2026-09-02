package model.markets;

public record OrderBook(String marketId, String marketDescription, OutcomeLadders[] outcomeLadders) {
}
