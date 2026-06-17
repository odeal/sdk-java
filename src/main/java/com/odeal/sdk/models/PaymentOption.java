package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class PaymentOption {
    /**
     * 
     */
    @JsonProperty("amount")
    @NotNull(message = "Amount cannot be null")
    @Valid
    private Double amount;
    /**
     * 
     */
    @JsonProperty("type")
    @NotNull(message = "Type cannot be null")
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
