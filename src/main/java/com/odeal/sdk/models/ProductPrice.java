package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class ProductPrice {
    /**
     * 
     */
    @JsonProperty("grossPrice")
    private Double grossPrice;
    /**
     * 
     */
    @JsonProperty("vatRatio")
    private Integer vatRatio;
    /**
     * 
     */
    @JsonProperty("sctRatio")
    private Integer sctRatio;
    public ProductPrice() {}
    @JsonProperty("grossPrice")
    public Double getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(Double grossPrice) {
        this.grossPrice = grossPrice;
    }
    @JsonProperty("vatRatio")
    public Integer getVatRatio() {
        return vatRatio;
    }

    public void setVatRatio(Integer vatRatio) {
        this.vatRatio = vatRatio;
    }
    @JsonProperty("sctRatio")
    public Integer getSctRatio() {
        return sctRatio;
    }

    public void setSctRatio(Integer sctRatio) {
        this.sctRatio = sctRatio;
    }
}
