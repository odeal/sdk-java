package com.odeal.sdk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Koşullu. Eğer ödeme tipi 'FOOD_CARD' ise ZORUNLUDUR.
    /// - 100001: Multinet
    /// - 100002: Setcard
    /// - 100003: Edenred
    /// - 100004: Tokenflex
    /// - 100005: Pluxee
    /// - 100006: Metropol
    /// - 100007: Paye
 */
public enum ReceiptInfoFoodCardBrandId {
    MULTINET(100001),
    SETCARD(100002),
    EDENRED(100003),
    TOKENFLEX(100004),
    PLUXEE(100005),
    METROPOL(100006),
    PAYE(100007);

    private final Integer value;

    ReceiptInfoFoodCardBrandId(Integer value) {
        this.value = value;
    }

    @JsonValue
    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @JsonCreator
    public static ReceiptInfoFoodCardBrandId fromValue(Integer value) {
        for (ReceiptInfoFoodCardBrandId b : ReceiptInfoFoodCardBrandId.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}