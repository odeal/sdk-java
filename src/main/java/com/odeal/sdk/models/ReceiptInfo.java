package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class ReceiptInfo {
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
    @JsonProperty("foodCardBrandId")
    private ReceiptInfoFoodCardBrandId foodCardBrandId;
    /**
     * Koşullu. Sadece 'CURRENT_ACCOUNT' (Cari Hesap) işleminde kullanılır (Fatura/Ekstre No).
     */
    @JsonProperty("receiptNumber")
    private String receiptNumber;
    /**
     * Koşullu. Sadece 'CURRENT_ACCOUNT' (Cari Hesap) işleminde kullanılır (YYYY-MM-DD).
     */
    @JsonProperty("receiptDate")
    private String receiptDate;
    /**
     * Opsiyonel.
     */
    @JsonProperty("SiparisNo")
    private String siparisNo;
    /**
     * Opsiyonel.
     */
    @JsonProperty("Garson")
    private String garson;
    public ReceiptInfo() {}
    @JsonProperty("foodCardBrandId")
    public ReceiptInfoFoodCardBrandId getFoodCardBrandId() {
        return foodCardBrandId;
    }

    public void setFoodCardBrandId(ReceiptInfoFoodCardBrandId foodCardBrandId) {
        this.foodCardBrandId = foodCardBrandId;
    }
    @JsonProperty("receiptNumber")
    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }
    @JsonProperty("receiptDate")
    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }
    @JsonProperty("SiparisNo")
    public String getSiparisNo() {
        return siparisNo;
    }

    public void setSiparisNo(String siparisNo) {
        this.siparisNo = siparisNo;
    }
    @JsonProperty("Garson")
    public String getGarson() {
        return garson;
    }

    public void setGarson(String garson) {
        this.garson = garson;
    }
}
