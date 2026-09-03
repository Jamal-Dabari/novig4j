package io.github.novig4j.model.markets;

import java.util.List;
import java.util.Optional;

public record Market(String id, String description, Status status, String type, League league, long volume, String eventId, List<String> outcomeIds, Outcomes outcomes, Optional<Double> strike, Event event, Optional<String> playerId, Optional<Player> player, Optional<Competitor> competitor, Optional<String> settledAt) {

    public Market {
        outcomeIds = List.copyOf(outcomeIds);

    }

}
