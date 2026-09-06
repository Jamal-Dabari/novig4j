package io.github.novig4j.model.markets;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum League {
    NFL, NBA, MLB,
    NHL, NCAAF, NCAAB,
    NCAAWB, WNBA, UFC,
    MLS, FIFACLUBWORLDCUP,
    EPL, BUNDESLIGA, SERIEA,
    LALIGA, LIGUE1, CHAMPIONSLEAGUE,
    EUROPALEAGUE, WTA, ATP, PGA, ENTERTAINMENT, @JsonEnumDefaultValue UNKNOWN;
}
