package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class CancelPaymentResult {
    /**
     * 
     */
    @JsonProperty("basketReferenceCode")
    @Valid
    private String basketReferenceCode;
    /**
     * 
     */
    @JsonProperty("message")
    @Valid
    private String message;
    public CancelPaymentResult() {}
    @JsonProperty("basketReferenceCode")
    public String getBasketReferenceCode() {
        return basketReferenceCode;
    }

    public void setBasketReferenceCode(String basketReferenceCode) {
        this.basketReferenceCode = basketReferenceCode;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
