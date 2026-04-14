package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class Product {
    /**
     * Birim Kodları.
    /// - _3I: Kilogram-Adet (C# Uyumu için ön ekli)
     */
    @JsonProperty("unitCode")
    private ProductUnitCode unitCode;
    /**
     * 
     */
    @JsonProperty("name")
    private String name;
    /**
     * 
     */
    @JsonProperty("referenceCode")
    private String referenceCode;
    /**
     * 
     */
    @JsonProperty("price")
    private ProductPrice price;
    /**
     * 
     */
    @JsonProperty("exemption")
    private Exemption exemption;
    public Product() {}
    @JsonProperty("unitCode")
    public ProductUnitCode getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(ProductUnitCode unitCode) {
        this.unitCode = unitCode;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("referenceCode")
    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
    @JsonProperty("price")
    public ProductPrice getPrice() {
        return price;
    }

    public void setPrice(ProductPrice price) {
        this.price = price;
    }
    @JsonProperty("exemption")
    public Exemption getExemption() {
        return exemption;
    }

    public void setExemption(Exemption exemption) {
        this.exemption = exemption;
    }
}
