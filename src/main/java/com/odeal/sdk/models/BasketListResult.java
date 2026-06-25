package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class BasketListResult {
    /**
     * 
     */
    @JsonProperty("baskets")
    @Valid
    private List<BasketSummary> baskets = new java.util.ArrayList<>();
    /**
     * 
     */
    @JsonProperty("totalPages")
    @Valid
    private Integer totalPages;
    /**
     * 
     */
    @JsonProperty("totalElements")
    @Valid
    private Integer totalElements;
    public BasketListResult() {}
    @JsonProperty("baskets")
    public List<BasketSummary> getBaskets() {
        return baskets;
    }

    public void setBaskets(List<BasketSummary> baskets) {
        this.baskets = baskets;
    }
    @JsonProperty("totalPages")
    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
    @JsonProperty("totalElements")
    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }
}
