
package io.github.novig4j.model;

import io.github.novig4j.model.markets.Currency;
import io.github.novig4j.model.markets.Status;

public record Order(String id, String outcomeId, String marketId, long price, long qty, long originalQty, Currency currency, Status status, String flags){

}