package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class ErrorResponse {
    /**
     * İş kuralı hata kodu (ör. 2029).
     */
    @JsonProperty("code")
    @Valid
    private Integer code;
    /**
     * Hata tipi (ör. BUSINESS, VALIDATION).
     */
    @JsonProperty("exceptionType")
    @Valid
    private String exceptionType;
    /**
     * Teknik hata açıklaması.
     */
    @JsonProperty("message")
    @Valid
    private String message;
    /**
     * Son kullanıcıya gösterilebilecek mesaj.
     */
    @JsonProperty("userMessage")
    @Valid
    private String userMessage;
    public ErrorResponse() {}
    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
    @JsonProperty("exceptionType")
    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @JsonProperty("userMessage")
    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
