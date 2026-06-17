package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class BasketListResponse {
    /**
     * 
     */
    @JsonProperty("result")
    @Valid
    private Object result;
    public BasketListResponse() {}
    @JsonProperty("result")
    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
