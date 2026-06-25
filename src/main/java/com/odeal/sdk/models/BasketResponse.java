package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class BasketResponse {
    /**
     * Oluşturulan sepetin sonucu.
     */
    @JsonProperty("result")
    @Valid
    private BasketCreateResult result;
    public BasketResponse() {}
    @JsonProperty("result")
    public BasketCreateResult getResult() {
        return result;
    }

    public void setResult(BasketCreateResult result) {
        this.result = result;
    }
}
