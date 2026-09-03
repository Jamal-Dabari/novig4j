package io.github.novig4j.model.markets;

import java.util.List;

public record OrderBook(String marketId, String marketDescription, List<OutcomeLadders> outcomeLadders) {
    public OrderBook {
        outcomeLadders = List.copyOf(outcomeLadders);
    }
}
