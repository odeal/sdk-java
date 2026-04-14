package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class BasketPrice {
    /**
     * 
     */
    @JsonProperty("grossPrice")
    private Double grossPrice;
    public BasketPrice() {}
    @JsonProperty("grossPrice")
    public Double getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(Double grossPrice) {
        this.grossPrice = grossPrice;
    }
}
