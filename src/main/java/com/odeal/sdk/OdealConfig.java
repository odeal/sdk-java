package com.odeal.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OdealConfig {
    private String secretKey;
    private String merchantKey;
    private String baseUrl = "https://api.odeal.com/v1";
    private String externalDeviceKey;
    private boolean skipClientValidation = false;
    private boolean debugMode = false;
    
    // --- ENTERPRISE YETENEKLERİ ---
    private int timeoutMs = 30000;
    private int maxRetryCount = 3;
    private boolean maskSensitiveData = true;
    private Logger logger = LoggerFactory.getLogger("odeal-sdk");
    private java.util.List<OdealInterceptor> interceptors = new java.util.ArrayList<>();
    
    // --- CIRCUIT BREAKER ---
    private boolean circuitBreakerEnabled = false;
    private int circuitBreakerThreshold = 5;
    private int circuitBreakerResetMs = 60000;

    /** Sandbox (test) modu. True olduğunda baseUrl değerine bakılmaksızın staging ortamı kullanılır. */
    private boolean sandboxMode = false;

    public OdealConfig() {}

    /** Fluent builder ile OdealConfig oluşturmak için. */
    public static OdealConfigBuilder builder() { return new OdealConfigBuilder(); }

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

    public int getTimeoutMs() { return timeoutMs; }
    public void setTimeoutMs(int timeoutMs) { this.timeoutMs = timeoutMs; }

    public int getMaxRetryCount() { return maxRetryCount; }
    public void setMaxRetryCount(int maxRetryCount) { this.maxRetryCount = maxRetryCount; }

    public boolean isMaskSensitiveData() { return maskSensitiveData; }
    public void setMaskSensitiveData(boolean maskSensitiveData) { this.maskSensitiveData = maskSensitiveData; }

    public Logger getLogger() { return logger; }
    public void setLogger(Logger logger) { this.logger = logger; }

    public java.util.List<OdealInterceptor> getInterceptors() { return interceptors; }
    public void setInterceptors(java.util.List<OdealInterceptor> interceptors) { this.interceptors = interceptors; }

    public boolean isCircuitBreakerEnabled() { return circuitBreakerEnabled; }
    public void setCircuitBreakerEnabled(boolean v) { this.circuitBreakerEnabled = v; }
    public int getCircuitBreakerThreshold() { return circuitBreakerThreshold; }
    public void setCircuitBreakerThreshold(int v) { this.circuitBreakerThreshold = v; }
    public int getCircuitBreakerResetMs() { return circuitBreakerResetMs; }
    public void setCircuitBreakerResetMs(int v) { this.circuitBreakerResetMs = v; }

    public boolean isSandboxMode() { return sandboxMode; }
    public void setSandboxMode(boolean sandboxMode) { this.sandboxMode = sandboxMode; }

    /** Efektif base URL — sandboxMode aktifse staging, değilse baseUrl döner. */
    public String getEffectiveBaseUrl() {
        return sandboxMode ? OdealEnvironment.STAGING.getBaseUrl() : baseUrl;
    }
    // --- GÜVENLİK ---

    @Override
    public String toString() {
        return "OdealConfig{" +
            "baseUrl='" + baseUrl + '\'' +
            ", secretKey='" + maskSecret(secretKey) + '\'' +
            ", merchantKey='" + maskSecret(merchantKey) + '\'' +
            ", debugMode=" + debugMode +
            ", timeoutMs=" + timeoutMs +
            ", maxRetryCount=" + maxRetryCount +
            '}';
    }

    private static String maskSecret(String secret) {
        if (secret == null || secret.isEmpty()) return "(empty)";
        if (secret.length() <= 8) return "***";
        return secret.substring(0, 4) + "***" + secret.substring(secret.length() - 4);
    }
}
