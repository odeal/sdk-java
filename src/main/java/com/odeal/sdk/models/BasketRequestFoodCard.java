package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class BasketRequestFoodCard {
    /**
     * 
     */
    @JsonProperty("referenceCode")
    @NotNull(message = "ReferenceCode cannot be null")
    @Valid
    @Pattern(regexp = "^.{1,50}$", message = "Referans kodu 1-50 karakter arasında olmalıdır.")
    private String referenceCode;
    /**
     * 
     */
    @JsonProperty("externalDeviceKey")
    @Valid
    @Pattern(regexp = "^.{1,}$", message = "Cihaz kodu boş olamaz.")
    private String externalDeviceKey;
    /**
     * Default: SIMPLE
     */
    @JsonProperty("basketType")
    private BasketType basketType;
    /**
     * Zorunlu. FoodCardBrandId içermelidir.
     */
    @JsonProperty("receiptInfo")
    @NotNull(message = "ReceiptInfo cannot be null")
    @Valid
    private ReceiptInfo receiptInfo;
    /**
     * 
     */
    @JsonProperty("customer")
    @NotNull(message = "Customer cannot be null")
    @Valid
    private Customer customer;
    /**
     * 
     */
    @JsonProperty("price")
    @NotNull(message = "Price cannot be null")
    @Valid
    private BasketPrice price;
    /**
     * Zorunlu.
     */
    @JsonProperty("items")
    @NotNull(message = "Items cannot be null")
    @Valid
    private List<Item> items;
    /**
     * 
     */
    @JsonProperty("paymentOptions")
    @NotNull(message = "PaymentOptions cannot be null")
    @Valid
    private List<Object> paymentOptions;
    public BasketRequestFoodCard() {}
    @JsonProperty("referenceCode")
    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
    @JsonProperty("externalDeviceKey")
    public String getExternalDeviceKey() {
        return externalDeviceKey;
    }

    public void setExternalDeviceKey(String externalDeviceKey) {
        this.externalDeviceKey = externalDeviceKey;
    }
    @JsonProperty("basketType")
    public BasketType getBasketType() {
        return basketType;
    }

    public void setBasketType(BasketType basketType) {
        this.basketType = basketType;
    }
    @JsonProperty("receiptInfo")
    public ReceiptInfo getReceiptInfo() {
        return receiptInfo;
    }

    public void setReceiptInfo(ReceiptInfo receiptInfo) {
        this.receiptInfo = receiptInfo;
    }
    @JsonProperty("customer")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @JsonProperty("price")
    public BasketPrice getPrice() {
        return price;
    }

    public void setPrice(BasketPrice price) {
        this.price = price;
    }
    @JsonProperty("items")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    @JsonProperty("paymentOptions")
    public List<Object> getPaymentOptions() {
        return paymentOptions;
    }

    public void setPaymentOptions(List<Object> paymentOptions) {
        this.paymentOptions = paymentOptions;
    }
}
