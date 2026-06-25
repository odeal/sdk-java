package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class CancelPaymentResponse {
    /**
     * Ödeme iptal sonucu.
     */
    @JsonProperty("result")
    @Valid
    private CancelPaymentResult result;
    public CancelPaymentResponse() {}
    @JsonProperty("result")
    public CancelPaymentResult getResult() {
        return result;
    }

    public void setResult(CancelPaymentResult result) {
        this.result = result;
    }
}
