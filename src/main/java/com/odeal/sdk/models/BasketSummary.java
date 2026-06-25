package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class BasketSummary {
    /**
     * 
     */
    @JsonProperty("id")
    @Valid
    private Integer id;
    /**
     * 
     */
    @JsonProperty("referenceCode")
    @Valid
    private String referenceCode;
    /**
     * 
     */
    @JsonProperty("basketAlias")
    @Valid
    private String basketAlias;
    /**
     * 
     */
    @JsonProperty("basketType")
    @Valid
    private String basketType;
    /**
     * 
     */
    @JsonProperty("status")
    @Valid
    private String status;
    /**
     * 
     */
    @JsonProperty("netPrice")
    @Valid
    private Double netPrice;
    /**
     * 
     */
    @JsonProperty("grossPrice")
    @Valid
    private Double grossPrice;
    /**
     * 
     */
    @JsonProperty("vatPrice")
    @Valid
    private Double vatPrice;
    /**
     * 
     */
    @JsonProperty("sctPrice")
    @Valid
    private Double sctPrice;
    /**
     * 
     */
    @JsonProperty("accPrice")
    @Valid
    private Double accPrice;
    /**
     * 
     */
    @JsonProperty("customerId")
    @Valid
    private Integer customerId;
    /**
     * 
     */
    @JsonProperty("deviceId")
    @Valid
    private Integer deviceId;
    /**
     * 
     */
    @JsonProperty("createdDate")
    @Valid
    private String createdDate;
    public BasketSummary() {}
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @JsonProperty("referenceCode")
    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
    @JsonProperty("basketAlias")
    public String getBasketAlias() {
        return basketAlias;
    }

    public void setBasketAlias(String basketAlias) {
        this.basketAlias = basketAlias;
    }
    @JsonProperty("basketType")
    public String getBasketType() {
        return basketType;
    }

    public void setBasketType(String basketType) {
        this.basketType = basketType;
    }
    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @JsonProperty("netPrice")
    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }
    @JsonProperty("grossPrice")
    public Double getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(Double grossPrice) {
        this.grossPrice = grossPrice;
    }
    @JsonProperty("vatPrice")
    public Double getVatPrice() {
        return vatPrice;
    }

    public void setVatPrice(Double vatPrice) {
        this.vatPrice = vatPrice;
    }
    @JsonProperty("sctPrice")
    public Double getSctPrice() {
        return sctPrice;
    }

    public void setSctPrice(Double sctPrice) {
        this.sctPrice = sctPrice;
    }
    @JsonProperty("accPrice")
    public Double getAccPrice() {
        return accPrice;
    }

    public void setAccPrice(Double accPrice) {
        this.accPrice = accPrice;
    }
    @JsonProperty("customerId")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    @JsonProperty("deviceId")
    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }
    @JsonProperty("createdDate")
    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
