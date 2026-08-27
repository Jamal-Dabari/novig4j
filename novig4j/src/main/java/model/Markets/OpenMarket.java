package model.Markets;

import java.util.Optional;

public record OpenMarket(String id, String description, String status, String type, String league, long volume, String eventid, String[] outcomeIds, Outcomes[] outcomes,
                         Optional<Double> strike, Event event, Optional<String> playerId, Optional<Player> player, Optional<Competitor> competitor, Optional<String> settledAt ) {
}
