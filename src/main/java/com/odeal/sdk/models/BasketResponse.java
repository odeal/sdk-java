package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class BasketResponse {
    /**
     * 
     */
    @JsonProperty("result")
    private Object result;
    public BasketResponse() {}
    @JsonProperty("result")
    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
