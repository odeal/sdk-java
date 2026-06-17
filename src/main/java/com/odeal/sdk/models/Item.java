package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class Item {
    /**
     * 
     */
    @JsonProperty("quantity")
    @NotNull(message = "Quantity cannot be null")
    @Valid
    private Integer quantity;
    /**
     * 
     */
    @JsonProperty("product")
    @NotNull(message = "Product cannot be null")
    @Valid
    private Product product;
    public Item() {}
    @JsonProperty("quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @JsonProperty("product")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
