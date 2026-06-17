package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class ValidationError {
    /**
     * Hatali alanin adi.
     */
    @JsonProperty("field")
    @Valid
    private String field;
    /**
     * Validasyon hata mesaji.
     */
    @JsonProperty("message")
    @Valid
    private String message;
    /**
     * Reddedilen deger.
     */
    @JsonProperty("rejectedValue")
    @Valid
    private String rejectedValue;
    public ValidationError() {}
    @JsonProperty("field")
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @JsonProperty("rejectedValue")
    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
