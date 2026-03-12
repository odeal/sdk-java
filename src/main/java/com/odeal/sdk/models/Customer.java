package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.Valid;
public class Customer {
    /**
     * Opsiyonel. Müşterinin kendi referans kodu.
     */
    @JsonProperty("referenceCode")
    @Pattern(regexp = "^.{0,50}$", message = "ReferenceCode formatı geçersiz.")
    private String referenceCode;
    /**
     * Müşteri Tipi. Default: INDIVIDUAL.
     */
    @JsonProperty("type")
    private CustomerType type;
    /**
     * 
     */
    @JsonProperty("name")
    @Pattern(regexp = "^.{0,100}$", message = "Name formatı geçersiz.")
    private String name;
    /**
     * 
     */
    @JsonProperty("surname")
    @Pattern(regexp = "^.{0,100}$", message = "Surname formatı geçersiz.")
    private String surname;
    /**
     * 
     */
    @JsonProperty("identityNumber")
    @Pattern(regexp = "^[1-9]\\d{10}$", message = "TCKN 11 haneli olmalı.")
    private String identityNumber;
    /**
     * 
     */
    @JsonProperty("title")
    @Pattern(regexp = "^.{0,255}$", message = "Title formatı geçersiz.")
    private String title;
    /**
     * 
     */
    @JsonProperty("taxNumber")
    @Pattern(regexp = "^\\d{10}$", message = "VKN 10 haneli olmalı.")
    private String taxNumber;
    /**
     * 
     */
    @JsonProperty("taxOffice")
    @Pattern(regexp = "^.{0,100}$", message = "TaxOffice formatı geçersiz.")
    private String taxOffice;
    /**
     * 
     */
    @JsonProperty("city")
    @NotNull(message = "City cannot be null")
    @Pattern(regexp = "^.{1,50}$", message = "City formatı geçersiz.")
    private String city;
    /**
     * 
     */
    @JsonProperty("town")
    @NotNull(message = "Town cannot be null")
    @Pattern(regexp = "^.{1,50}$", message = "Town formatı geçersiz.")
    private String town;
    /**
     * 
     */
    @JsonProperty("gsmNumber")
    @Pattern(regexp = "^[1-9][0-9]{9}$", message = "GSM No başında 0 olmadan 10 hane olmalıdır.")
    private String gsmNumber;
    /**
     * 
     */
    @JsonProperty("email")
    @Pattern(regexp = "^.{0,100}$", message = "Email formatı geçersiz.")
    private String email;
    /**
     * 
     */
    @JsonProperty("address")
    @Pattern(regexp = "^.{0,500}$", message = "Address formatı geçersiz.")
    private String address;
    public Customer() {}
    @JsonProperty("referenceCode")
    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }
    @JsonProperty("type")
    public CustomerType getType() {
        return type;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @JsonProperty("surname")
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    @JsonProperty("identityNumber")
    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @JsonProperty("taxNumber")
    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }
    @JsonProperty("taxOffice")
    public String getTaxOffice() {
        return taxOffice;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }
    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @JsonProperty("town")
    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
    @JsonProperty("gsmNumber")
    public String getGsmNumber() {
        return gsmNumber;
    }

    public void setGsmNumber(String gsmNumber) {
        this.gsmNumber = gsmNumber;
    }
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
