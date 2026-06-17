package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class Product {
    /**
     * Birim Kodları.
    /// - _3I: Kilogram-Adet (C# Uyumu için ön ekli)
     */
    @JsonProperty("unitCode")
    @NotNull(message = "UnitCode cannot be null")
    private ProductUnitCode unitCode;
    /**
     * 
     */
    @JsonProperty("name")
    @NotNull(message = "Name cannot be null")
    @Valid
    @Pattern(regexp = "^.{1,255}$", message = "Ürün adı 1-255 karakter arasında olmalıdır.")
    private String name;
    /**
     * 
     */
    @JsonProperty("referenceCode")
    @NotNull(message = "ReferenceCode cannot be null")
    @Valid
    @Pattern(regexp = "^.{1,50}$", message = "Ürün kodu 1-50 karakter arasında olmalıdır.")
    private String referenceCode;
    /**
     * 
     */
    @JsonProperty("price")
    @NotNull(message = "Price cannot be null")
    @Valid
    private ProductPrice price;
    /**
     * 
     */
    @JsonProperty("exemption")
    @Valid
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
