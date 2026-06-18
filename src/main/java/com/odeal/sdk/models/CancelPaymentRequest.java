package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class CancelPaymentRequest {
    /**
     * İptal edilecek sepetin referans kodu
     */
    @JsonProperty("basketReferenceCode")
    @NotNull(message = "BasketReferenceCode cannot be null")
    @Valid
    @Pattern(regexp = "^.{1,50}$", message = "Referans kodu 1-50 karakter arasında olmalıdır.")
    private String basketReferenceCode;
    public CancelPaymentRequest() {}
    @JsonProperty("basketReferenceCode")
    public String getBasketReferenceCode() {
        return basketReferenceCode;
    }

    public void setBasketReferenceCode(String basketReferenceCode) {
        this.basketReferenceCode = basketReferenceCode;
    }
}
