package com.odeal.sdk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 */
public enum PaymentOptionType {
    CASH("CASH"),
    CREDITCARD("CREDITCARD"),
    GIFT("GIFT"),
    OPENACCOUNT("OPEN_ACCOUNT"),
    FOODCARD("FOOD_CARD");

    private final String value;

    PaymentOptionType(String value) {
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
    public static PaymentOptionType fromValue(String value) {
        for (PaymentOptionType b : PaymentOptionType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}