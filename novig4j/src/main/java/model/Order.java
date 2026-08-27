
package model;

public record Order(String id, String outcomeId, String marketId, double price, long qty, long originalQty, String currency, String status, String flags){

}