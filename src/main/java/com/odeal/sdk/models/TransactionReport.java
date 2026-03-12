package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class TransactionReport {
    /**
     * 
     */
    @JsonProperty("basketReferenceCode")
    private String basketReferenceCode;
    /**
     * 
     */
    @JsonProperty("paymentId")
    private String paymentId;
    /**
     * 
     */
    @JsonProperty("currentStatus")
    private String currentStatus;
    /**
     * 
     */
    @JsonProperty("amount")
    private String amount;
    /**
     * 
     */
    @JsonProperty("invoiceNumber")
    private String invoiceNumber;
    /**
     * 
     */
    @JsonProperty("invoicePdfUrl")
    private String invoicePdfUrl;
    /**
     * 
     */
    @JsonProperty("basketStatus")
    private String basketStatus;
    /**
     * 
     */
    @JsonProperty("invoiceGibStatusCode")
    private Integer invoiceGibStatusCode;
    public TransactionReport() {}
    @JsonProperty("basketReferenceCode")
    public String getBasketReferenceCode() {
        return basketReferenceCode;
    }

    public void setBasketReferenceCode(String basketReferenceCode) {
        this.basketReferenceCode = basketReferenceCode;
    }
    @JsonProperty("paymentId")
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
    @JsonProperty("currentStatus")
    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }
    @JsonProperty("amount")
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
    @JsonProperty("invoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
    @JsonProperty("invoicePdfUrl")
    public String getInvoicePdfUrl() {
        return invoicePdfUrl;
    }

    public void setInvoicePdfUrl(String invoicePdfUrl) {
        this.invoicePdfUrl = invoicePdfUrl;
    }
    @JsonProperty("basketStatus")
    public String getBasketStatus() {
        return basketStatus;
    }

    public void setBasketStatus(String basketStatus) {
        this.basketStatus = basketStatus;
    }
    @JsonProperty("invoiceGibStatusCode")
    public Integer getInvoiceGibStatusCode() {
        return invoiceGibStatusCode;
    }

    public void setInvoiceGibStatusCode(Integer invoiceGibStatusCode) {
        this.invoiceGibStatusCode = invoiceGibStatusCode;
    }
}
