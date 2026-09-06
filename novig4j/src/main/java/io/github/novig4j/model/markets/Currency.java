package io.github.novig4j.model.markets;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Currency {
    CASH, COIN, @JsonEnumDefaultValue UNKNOWN;
}
