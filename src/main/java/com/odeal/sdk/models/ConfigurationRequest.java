package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class ConfigurationRequest {
    /**
     * 
     */
    @JsonProperty("eCommerceUrl")
    private String eCommerceUrl;
    /**
     * Sepet bilgisinin alınacağı URL (external-basket için).
     */
    @JsonProperty("basketUrl")
    private String basketUrl;
    /**
     * Ödeme başarılı bildirimi için URL.
     */
    @JsonProperty("paymentSucceededUrl")
    private String paymentSucceededUrl;
    /**
     * Ödeme başarısız bildirimi için URL.
     */
    @JsonProperty("paymentFailedUrl")
    private String paymentFailedUrl;
    /**
     * İptal bildirimi için URL.
     */
    @JsonProperty("paymentCancelledUrl")
    private String paymentCancelledUrl;
    /**
     * 
     */
    @JsonProperty("eInvoiceCreatedUrl")
    private String eInvoiceCreatedUrl;
    /**
     * 
     */
    @JsonProperty("eInvoiceIntegrator")
    private String eInvoiceIntegrator;
    /**
     * Webhook isteklerinde güvenlik için X-ODEAL-REQUEST-KEY olarak gönderilir.
     */
    @JsonProperty("odealRequestKey")
    private String odealRequestKey;
    public ConfigurationRequest() {}
    @JsonProperty("eCommerceUrl")
    public String getECommerceUrl() {
        return eCommerceUrl;
    }

    public void setECommerceUrl(String eCommerceUrl) {
        this.eCommerceUrl = eCommerceUrl;
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
    @JsonProperty("paymentFailedUrl")
    public String getPaymentFailedUrl() {
        return paymentFailedUrl;
    }

    public void setPaymentFailedUrl(String paymentFailedUrl) {
        this.paymentFailedUrl = paymentFailedUrl;
    }
    @JsonProperty("paymentCancelledUrl")
    public String getPaymentCancelledUrl() {
        return paymentCancelledUrl;
    }

    public void setPaymentCancelledUrl(String paymentCancelledUrl) {
        this.paymentCancelledUrl = paymentCancelledUrl;
    }
    @JsonProperty("eInvoiceCreatedUrl")
    public String getEInvoiceCreatedUrl() {
        return eInvoiceCreatedUrl;
    }

    public void setEInvoiceCreatedUrl(String eInvoiceCreatedUrl) {
        this.eInvoiceCreatedUrl = eInvoiceCreatedUrl;
    }
    @JsonProperty("eInvoiceIntegrator")
    public String getEInvoiceIntegrator() {
        return eInvoiceIntegrator;
    }

    public void setEInvoiceIntegrator(String eInvoiceIntegrator) {
        this.eInvoiceIntegrator = eInvoiceIntegrator;
    }
    @JsonProperty("odealRequestKey")
    public String getOdealRequestKey() {
        return odealRequestKey;
    }

    public void setOdealRequestKey(String odealRequestKey) {
        this.odealRequestKey = odealRequestKey;
    }
}
