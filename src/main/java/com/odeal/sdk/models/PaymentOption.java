package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class PaymentOption {
    /**
     * 
     */
    @JsonProperty("amount")
    private Double amount;
    /**
     * 
     */
    @JsonProperty("type")
    private PaymentOptionType type;
    public PaymentOption() {}
    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    @JsonProperty("type")
    public PaymentOptionType getType() {
        return type;
    }

    public void setType(PaymentOptionType type) {
        this.type = type;
    }
}
