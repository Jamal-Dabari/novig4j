package model.markets;

public class OutcomeLadders {
    private final String outcomeId;
    private final Bids bids;

    public OutcomeLadders(String outcomeId, Bids bids) {
        this.outcomeId = outcomeId;
        this.bids = bids;
    }
}
