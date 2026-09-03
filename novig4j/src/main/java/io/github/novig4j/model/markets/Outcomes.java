package io.github.novig4j.model.markets;

import java.util.Optional;

public record Outcomes(String id, String description, Optional<String> type, Status status, int index, Optional<Double> last, Optional<Competitor> competitor) {
}
