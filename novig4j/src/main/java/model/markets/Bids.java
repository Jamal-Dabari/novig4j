package model.markets;

import java.util.Date;

public class Bids {
    private final String id;
    private final double price;
    private final long qty;
    private final long oridinalQty;
    private final Currency currency;
    private final String outcomeId;
    private final String marketId;
    private final String status;
    private final Date createdAt;


    public Bids(String id, double price, long qty, long oridinalQty, Currency currency, String outcomeId, String marketId, String status, Date createdAt) {
        this.id = id;
        this.price = price;
        this.qty = qty;
        this.oridinalQty = oridinalQty;
        this.currency = currency;
        this.outcomeId = outcomeId;
        this.marketId = marketId;
        this.status = status;
        this.createdAt = createdAt;
    }
}
