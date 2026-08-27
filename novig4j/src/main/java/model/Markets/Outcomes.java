package model.Markets;

import java.util.Optional;

public class Outcomes {
    private final String id;
    private final String description,
    private final Optional<String> type;
    private final String status;
    private final int index;
    private final Optional<Double> last;
    private final Optional<Competitor> competitor;

}
