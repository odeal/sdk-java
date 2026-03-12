package com.odeal.sdk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Müşteri Tipi. Default: INDIVIDUAL.
 */
public enum CustomerType {
    INDIVIDUAL("INDIVIDUAL"),
    CORPORATE("CORPORATE");

    private final String value;

    CustomerType(String value) {
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
    public static CustomerType fromValue(String value) {
        for (CustomerType b : CustomerType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}