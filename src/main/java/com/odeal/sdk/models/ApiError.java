package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class ApiError {
    /**
     * HTTP status kodu.
     */
    @JsonProperty("code")
    private Integer code;
    /**
     * Kullaniciya gosterilebilecek hata mesaji.
     */
    @JsonProperty("message")
    private String message;
    /**
     * Opsiyonel teknik detay.
     */
    @JsonProperty("details")
    private String details;
    /**
     * Hatanin olustu zaman.
     */
    @JsonProperty("timestamp")
    private String timestamp;
    /**
     * Hatanin olustugu endpoint.
     */
    @JsonProperty("path")
    private String path;
    /**
     * Validasyon hatalari (400 icin).
     */
    @JsonProperty("validationErrors")
    private List<ValidationError> validationErrors;
    public ApiError() {}
    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @JsonProperty("details")
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    @JsonProperty("validationErrors")
    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    public void setValidationErrors(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
