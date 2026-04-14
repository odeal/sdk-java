package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class BasketRequestFoodCard {
    /**
     * 
     */
    @JsonProperty("referenceCode")
    private String referenceCode;
    /**
     * 
     */
    @JsonProperty("externalDeviceKey")
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
    private ReceiptInfo receiptInfo;
    /**
     * 
     */
    @JsonProperty("customer")
    private Customer customer;
    /**
     * 
     */
    @JsonProperty("price")
    private BasketPrice price;
    /**
     * Zorunlu.
     */
    @JsonProperty("items")
    private List<Item> items;
    /**
     * 
     */
    @JsonProperty("paymentOptions")
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
