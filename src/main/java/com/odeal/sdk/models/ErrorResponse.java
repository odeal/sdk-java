package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class ErrorResponse {
    /**
     * Hata kodu.
     */
    @JsonProperty("error")
    private String error;
    /**
     * Hata aciklamasi.
     */
    @JsonProperty("message")
    private String message;
    /**
     * Detayli hata listesi.
     */
    @JsonProperty("details")
    private List<String> details;
    public ErrorResponse() {}
    @JsonProperty("error")
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @JsonProperty("details")
    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }
}
