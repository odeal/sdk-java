package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class ConfigurationResponse {
    /**
     * 
     */
    @JsonProperty("ecommerceUrl")
    @Valid
    private String ecommerceUrl;
    /**
     * 
     */
    @JsonProperty("basketUrl")
    @Valid
    private String basketUrl;
    /**
     * 
     */
    @JsonProperty("paymentSucceededUrl")
    @Valid
    private String paymentSucceededUrl;
    /**
     * 
     */
    @JsonProperty("paymentCancelledUrl")
    @Valid
    private String paymentCancelledUrl;
    /**
     * 
     */
    @JsonProperty("paymentFailedUrl")
    @Valid
    private String paymentFailedUrl;
    /**
     * 
     */
    @JsonProperty("einvoiceCreatedUrl")
    @Valid
    private String einvoiceCreatedUrl;
    /**
     * 
     */
    @JsonProperty("einvoiceCancelledUrl")
    @Valid
    private String einvoiceCancelledUrl;
    /**
     * 
     */
    @JsonProperty("callbackPayoutUrl")
    @Valid
    private String callbackPayoutUrl;
    /**
     * 
     */
    @JsonProperty("basketCancelledUrl")
    @Valid
    private String basketCancelledUrl;
    /**
     * 
     */
    @JsonProperty("basketProcessFailedUrl")
    @Valid
    private String basketProcessFailedUrl;
    /**
     * 
     */
    @JsonProperty("einvoiceIntegrator")
    @Valid
    private String einvoiceIntegrator;
    /**
     * 
     */
    @JsonProperty("basketType")
    @Valid
    private String basketType;
    /**
     * 
     */
    @JsonProperty("odealRequestKey")
    @Valid
    private String odealRequestKey;
    /**
     * 
     */
    @JsonProperty("customerGetUrl")
    @Valid
    private String customerGetUrl;
    /**
     * 
     */
    @JsonProperty("customerPostUrl")
    @Valid
    private String customerPostUrl;
    /**
     * 
     */
    @JsonProperty("customerPutUrl")
    @Valid
    private String customerPutUrl;
    /**
     * 
     */
    @JsonProperty("intentUrl")
    @Valid
    private String intentUrl;
    /**
     * 
     */
    @JsonProperty("appName")
    @Valid
    private String appName;
    /**
     * 
     */
    @JsonProperty("closeAfterPayment")
    @Valid
    private Boolean closeAfterPayment;
    /**
     * 
     */
    @JsonProperty("authorizationType")
    @Valid
    private String authorizationType;
    /**
     * 
     */
    @JsonProperty("basicAuthUsername")
    @Valid
    private String basicAuthUsername;
    /**
     * 
     */
    @JsonProperty("basicAuthPassword")
    @Valid
    private String basicAuthPassword;
    /**
     * 
     */
    @JsonProperty("terminalId")
    @Valid
    private String terminalId;
    public ConfigurationResponse() {}
    @JsonProperty("ecommerceUrl")
    public String getEcommerceUrl() {
        return ecommerceUrl;
    }

    public void setEcommerceUrl(String ecommerceUrl) {
        this.ecommerceUrl = ecommerceUrl;
    }
    @JsonProperty("basketUrl")
    public String getBasketUrl() {
        return basketUrl;
    }

    public void setBasketUrl(String basketUrl) {
        this.basketUrl = basketUrl;
    }
    @JsonProperty("paymentSucceededUrl")
    public String getPaymentSucceededUrl() {
        return paymentSucceededUrl;
    }

    public void setPaymentSucceededUrl(String paymentSucceededUrl) {
        this.paymentSucceededUrl = paymentSucceededUrl;
    }
    @JsonProperty("paymentCancelledUrl")
    public String getPaymentCancelledUrl() {
        return paymentCancelledUrl;
    }

    public void setPaymentCancelledUrl(String paymentCancelledUrl) {
        this.paymentCancelledUrl = paymentCancelledUrl;
    }
    @JsonProperty("paymentFailedUrl")
    public String getPaymentFailedUrl() {
        return paymentFailedUrl;
    }

    public void setPaymentFailedUrl(String paymentFailedUrl) {
        this.paymentFailedUrl = paymentFailedUrl;
    }
    @JsonProperty("einvoiceCreatedUrl")
    public String getEinvoiceCreatedUrl() {
        return einvoiceCreatedUrl;
    }

    public void setEinvoiceCreatedUrl(String einvoiceCreatedUrl) {
        this.einvoiceCreatedUrl = einvoiceCreatedUrl;
    }
    @JsonProperty("einvoiceCancelledUrl")
    public String getEinvoiceCancelledUrl() {
        return einvoiceCancelledUrl;
    }

    public void setEinvoiceCancelledUrl(String einvoiceCancelledUrl) {
        this.einvoiceCancelledUrl = einvoiceCancelledUrl;
    }
    @JsonProperty("callbackPayoutUrl")
    public String getCallbackPayoutUrl() {
        return callbackPayoutUrl;
    }

    public void setCallbackPayoutUrl(String callbackPayoutUrl) {
        this.callbackPayoutUrl = callbackPayoutUrl;
    }
    @JsonProperty("basketCancelledUrl")
    public String getBasketCancelledUrl() {
        return basketCancelledUrl;
    }

    public void setBasketCancelledUrl(String basketCancelledUrl) {
        this.basketCancelledUrl = basketCancelledUrl;
    }
    @JsonProperty("basketProcessFailedUrl")
    public String getBasketProcessFailedUrl() {
        return basketProcessFailedUrl;
    }

    public void setBasketProcessFailedUrl(String basketProcessFailedUrl) {
        this.basketProcessFailedUrl = basketProcessFailedUrl;
    }
    @JsonProperty("einvoiceIntegrator")
    public String getEinvoiceIntegrator() {
        return einvoiceIntegrator;
    }

    public void setEinvoiceIntegrator(String einvoiceIntegrator) {
        this.einvoiceIntegrator = einvoiceIntegrator;
    }
    @JsonProperty("basketType")
    public String getBasketType() {
        return basketType;
    }

    public void setBasketType(String basketType) {
        this.basketType = basketType;
    }
    @JsonProperty("odealRequestKey")
    public String getOdealRequestKey() {
        return odealRequestKey;
    }

    public void setOdealRequestKey(String odealRequestKey) {
        this.odealRequestKey = odealRequestKey;
    }
    @JsonProperty("customerGetUrl")
    public String getCustomerGetUrl() {
        return customerGetUrl;
    }

    public void setCustomerGetUrl(String customerGetUrl) {
        this.customerGetUrl = customerGetUrl;
    }
    @JsonProperty("customerPostUrl")
    public String getCustomerPostUrl() {
        return customerPostUrl;
    }

    public void setCustomerPostUrl(String customerPostUrl) {
        this.customerPostUrl = customerPostUrl;
    }
    @JsonProperty("customerPutUrl")
    public String getCustomerPutUrl() {
        return customerPutUrl;
    }

    public void setCustomerPutUrl(String customerPutUrl) {
        this.customerPutUrl = customerPutUrl;
    }
    @JsonProperty("intentUrl")
    public String getIntentUrl() {
        return intentUrl;
    }

    public void setIntentUrl(String intentUrl) {
        this.intentUrl = intentUrl;
    }
    @JsonProperty("appName")
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
    @JsonProperty("closeAfterPayment")
    public Boolean getCloseAfterPayment() {
        return closeAfterPayment;
    }

    public void setCloseAfterPayment(Boolean closeAfterPayment) {
        this.closeAfterPayment = closeAfterPayment;
    }
    @JsonProperty("authorizationType")
    public String getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(String authorizationType) {
        this.authorizationType = authorizationType;
    }
    @JsonProperty("basicAuthUsername")
    public String getBasicAuthUsername() {
        return basicAuthUsername;
    }

    public void setBasicAuthUsername(String basicAuthUsername) {
        this.basicAuthUsername = basicAuthUsername;
    }
    @JsonProperty("basicAuthPassword")
    public String getBasicAuthPassword() {
        return basicAuthPassword;
    }

    public void setBasicAuthPassword(String basicAuthPassword) {
        this.basicAuthPassword = basicAuthPassword;
    }
    @JsonProperty("terminalId")
    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}
