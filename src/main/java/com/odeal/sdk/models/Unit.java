package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class Unit {
    /**
     * 
     */
    @JsonProperty("id")
    @Valid
    private Integer id;
    /**
     * 
     */
    @JsonProperty("code")
    @Valid
    private String code;
    /**
     * 
     */
    @JsonProperty("name")
    @Valid
    private String name;
    /**
     * 
     */
    @JsonProperty("decimal")
    @Valid
    private Boolean decimal;
    public Unit() {}
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("decimal")
    public Boolean getDecimal() {
        return decimal;
    }

    public void setDecimal(Boolean decimal) {
        this.decimal = decimal;
    }
}
