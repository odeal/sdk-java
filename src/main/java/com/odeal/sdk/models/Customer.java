package com.odeal.sdk.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odeal.sdk.enums.*;
import java.util.List;
import java.math.BigDecimal;
public class Customer {
    /**
     * Opsiyonel. Müşterinin kendi referans kodu.
     */
    @JsonProperty("referenceCode")
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
    private String name;
    /**
     * 
     */
    @JsonProperty("surname")
    private String surname;
    /**
     * 
     */
    @JsonProperty("identityNumber")
    private String identityNumber;
    /**
     * 
     */
    @JsonProperty("title")
    private String title;
    /**
     * 
     */
    @JsonProperty("taxNumber")
    private String taxNumber;
    /**
     * 
     */
    @JsonProperty("taxOffice")
    private String taxOffice;
    /**
     * 
     */
    @JsonProperty("city")
    private String city;
    /**
     * 
     */
    @JsonProperty("town")
    private String town;
    /**
     * 
     */
    @JsonProperty("gsmNumber")
    private String gsmNumber;
    /**
     * 
     */
    @JsonProperty("email")
    private String email;
    /**
     * 
     */
    @JsonProperty("address")
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
