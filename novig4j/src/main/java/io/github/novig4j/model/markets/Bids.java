package io.github.novig4j.model.markets;

import java.time.Instant;

public record Bids(String id, long price, long qty, long originalQty, Currency currency, String outcomeId, String marketId, Status status, Instant createdAt) {

}
