package model;

import java.util.Date;

public record Fill(String id, String orderId, double price, long qty, Date createdAt, boolean isWash, boolean isTaker, String marketId, String outcomeId) {
}
