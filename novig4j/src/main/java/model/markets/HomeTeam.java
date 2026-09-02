package model.markets;

public class HomeTeam {
    private final String id;
    private final String name;
    private final String shortName;
    private final String symbol;
    private final String mascot;

    public HomeTeam(String id, String name, String shortName, String symbol, String mascot) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.symbol = symbol;
        this.mascot = mascot;
    }
}
