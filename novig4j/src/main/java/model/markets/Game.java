package model.markets;

import java.util.Date;

public class Game {
    private final String id;
    private final String league;
    private final String status;
    private final Date scheduledStart;
    private final HomeTeam homeTeam;
    private final AwayTeam awayTeam;


    public Game(String id, String league, String status, Date scheduledStart, HomeTeam homeTeam, AwayTeam awayTeam) {
        this.id = id;
        this.league = league;
        this.status = status;
        this.scheduledStart = scheduledStart;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
}
