package io.github.novig4j.model.markets;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Status {
    CANCELLED, CLOSED_PREGAME, DELAYED, FINAL, INITIAL, OPEN_INGAME, OPEN_PREGAME, @JsonEnumDefaultValue UNKNOWN;
}
