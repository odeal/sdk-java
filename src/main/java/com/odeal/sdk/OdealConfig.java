package com.odeal.sdk;

public class OdealConfig {
    private String secretKey;
    private String merchantKey;
    private String baseUrl = "https://api.odeal.com/v1";
    private String externalDeviceKey;
    private boolean skipClientValidation = false;
    private boolean debugMode = false;

    public OdealConfig() {}

    public OdealConfig(String secretKey, String merchantKey, String baseUrl, String externalDeviceKey, boolean skipClientValidation, boolean debugMode) {
        this.secretKey = secretKey;
        this.merchantKey = merchantKey;
        this.baseUrl = baseUrl != null ? baseUrl : "https://api.odeal.com/v1";
        this.externalDeviceKey = externalDeviceKey;
        this.skipClientValidation = skipClientValidation;
        this.debugMode = debugMode;
    }

    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }

    public String getMerchantKey() { return merchantKey; }
    public void setMerchantKey(String merchantKey) { this.merchantKey = merchantKey; }

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

    public String getExternalDeviceKey() { return externalDeviceKey; }
    public void setExternalDeviceKey(String externalDeviceKey) { this.externalDeviceKey = externalDeviceKey; }

    public boolean isSkipClientValidation() { return skipClientValidation; }
    public void setSkipClientValidation(boolean skipClientValidation) { this.skipClientValidation = skipClientValidation; }

    public boolean isDebugMode() { return debugMode; }
    public void setDebugMode(boolean debugMode) { this.debugMode = debugMode; }
}
