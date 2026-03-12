package com.odeal.sdk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Global Basket Type Enum.
 */
public enum BasketType {
    SIMPLE("SIMPLE"),
    ADVANCE("ADVANCE"),
    CURRENTACCOUNT("CURRENT_ACCOUNT");

    private final String value;

    BasketType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static BasketType fromValue(String value) {
        for (BasketType b : BasketType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}