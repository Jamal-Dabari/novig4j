package io.github.novig4j.model.markets;

import java.time.Instant;

public record Game (String id, League league, Status status, Instant scheduledStart, HomeTeam homeTeam, AwayTeam awayTeam) {
}
