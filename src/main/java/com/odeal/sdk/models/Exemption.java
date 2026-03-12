package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class Exemption {
    /**
     * 
     */
    @JsonProperty("code")
    @Pattern(regexp = "^.{1,}$", message = "Code formatı geçersiz.")
    private String code;
    /**
     * 
     */
    @JsonProperty("description")
    private String description;
    public Exemption() {}
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
