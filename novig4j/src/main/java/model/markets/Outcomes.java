package model.markets;

import java.util.Optional;

public class Outcomes {
    private final String id;
    private final String description;
    private final Optional<String> type;
    private final String status;
    private final int index;
    private final Optional<Double> last;
    private final Optional<Competitor> competitor;

    public Outcomes(String id, String description, Optional<String> type, String status, int index, Optional<Double> last, Optional<Competitor> competitor) {
        this.id = id;
        this.description = description;
        this.type = type;
        this.status = status;
        this.index = index;
        this.last = last;
        this.competitor = competitor;
    }


}
