package com.odeal.sdk.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Birim Kodları.
    /// - _3I: Kilogram-Adet (C# Uyumu için ön ekli)
 */
public enum ProductUnitCode {
    C62("C62"),
    CTM("CTM"),
    GRM("GRM"),
    GT("GT"),
    MND("MND"),
    KGM("KGM"),
    LTR("LTR"),
    MTK("MTK"),
    KWH("KWH"),
    MTQ("MTQ"),
    MTR("MTR"),
    CMT("CMT"),
    B32("B32"),
    CCT("CCT"),
    PR("PR"),
    D30("D30"),
    GFI("GFI"),
    KPO("KPO"),
    _3I("3I"),
    KFO("KFO"),
    KHY("KHY"),
    KMA("KMA"),
    KNI("KNI"),
    KPH("KPH"),
    KSH("KSH"),
    KUR("KUR"),
    D32("D32"),
    GWH("GWH"),
    MWH("MWH"),
    KWT("KWT"),
    LPA("LPA"),
    DMK("DMK"),
    NCL("NCL"),
    SM3("SM3"),
    R9("R9"),
    SET("SET"),
    T3("T3"),
    AD("AD"),
    PA("PA"),
    PK("PK");

    private final String value;

    ProductUnitCode(String value) {
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
    public static ProductUnitCode fromValue(String value) {
        for (ProductUnitCode b : ProductUnitCode.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}