package com.odeal.sdk;

import org.slf4j.Logger;
import java.util.ArrayList;
import java.util.List;

/**
 * OdealConfig için fluent builder sınıfı.
 *
 * <pre>{@code
 * OdealConfig config = OdealConfig.builder()
 *     .secretKey("sk_xxx")
 *     .merchantKey("mk_xxx")
 *     .baseUrl("https://api.odeal.com/v1")
 *     .debugMode(true)
 *     .timeoutMs(60000)
 *     .maxRetryCount(5)
 *     .addInterceptor(new LoggingInterceptor())
 *     .build();
 * }</pre>
 */
public class OdealConfigBuilder {
    private String secretKey;
    private String merchantKey;
    private String baseUrl = "https://api.odeal.com/v1";
    private String externalDeviceKey;
    private boolean skipClientValidation = false;
    private boolean debugMode = false;
    private int timeoutMs = 30000;
    private int maxRetryCount = 3;
    private boolean maskSensitiveData = true;
    private Logger logger;
    private final List<OdealInterceptor> interceptors = new ArrayList<>();

    OdealConfigBuilder() {}

    public OdealConfigBuilder secretKey(String secretKey) { this.secretKey = secretKey; return this; }
    public OdealConfigBuilder merchantKey(String merchantKey) { this.merchantKey = merchantKey; return this; }
    public OdealConfigBuilder baseUrl(String baseUrl) { this.baseUrl = baseUrl; return this; }
    /** Ortam preset'i. BaseUrl'i otomatik ayarlar. */
    public OdealConfigBuilder environment(OdealEnvironment env) { this.baseUrl = env.getBaseUrl(); return this; }
    /** Sandbox (test) modu. Aktifken her zaman staging ortamı kullanılır. */
    public OdealConfigBuilder sandboxMode(boolean sandbox) { if (sandbox) this.baseUrl = OdealEnvironment.STAGING.getBaseUrl(); return this; }
    public OdealConfigBuilder externalDeviceKey(String key) { this.externalDeviceKey = key; return this; }
    public OdealConfigBuilder skipClientValidation(boolean skip) { this.skipClientValidation = skip; return this; }
    public OdealConfigBuilder debugMode(boolean debug) { this.debugMode = debug; return this; }
    public OdealConfigBuilder timeoutMs(int timeoutMs) { this.timeoutMs = timeoutMs; return this; }
    public OdealConfigBuilder maxRetryCount(int maxRetryCount) { this.maxRetryCount = maxRetryCount; return this; }
    public OdealConfigBuilder maskSensitiveData(boolean mask) { this.maskSensitiveData = mask; return this; }
    public OdealConfigBuilder logger(Logger logger) { this.logger = logger; return this; }
    public OdealConfigBuilder addInterceptor(OdealInterceptor interceptor) { this.interceptors.add(interceptor); return this; }

    /**
     * OdealConfig'i oluşturur ve zorunlu alanları doğrular.
     *
     * @return Yapılandırılmış OdealConfig
     * @throws IllegalStateException Zorunlu alanlar eksikse
     */
    public OdealConfig build() {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException("secretKey is required. Call secretKey().");
        }
        if (merchantKey == null || merchantKey.isEmpty()) {
            throw new IllegalStateException("merchantKey is required. Call merchantKey().");
        }

        OdealConfig config = new OdealConfig();
        config.setSecretKey(secretKey);
        config.setMerchantKey(merchantKey);
        config.setBaseUrl(baseUrl);
        config.setExternalDeviceKey(externalDeviceKey);
        config.setSkipClientValidation(skipClientValidation);
        config.setDebugMode(debugMode);
        config.setTimeoutMs(timeoutMs);
        config.setMaxRetryCount(maxRetryCount);
        config.setMaskSensitiveData(maskSensitiveData);
        if (logger != null) config.setLogger(logger);
        config.setInterceptors(interceptors);
        return config;
    }
}
