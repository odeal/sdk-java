package com.odeal.sdk;

/**
 * Spring Boot AutoConfiguration desteği.
 * 
 * <p>Spring Boot uygulamalarında OdealClient'ı otomatik yapılandırır.
 * {@code application.yml} veya {@code application.properties} dosyasından
 * yapılandırma okur.</p>
 * 
 * <h3>application.yml örneği:</h3>
 * <pre>{@code
 * odeal:
 *   secret-key: sk_xxx
 *   merchant-key: mk_xxx
 *   base-url: https://api.odeal.com/v1
 *   debug-mode: false
 *   timeout-ms: 30000
 *   max-retry-count: 3
 * }</pre>
 * 
 * <h3>Kullanım:</h3>
 * <pre>{@code
 * @Service
 * public class PaymentService {
 *     private final OdealClient odealClient;
 *     
 *     public PaymentService(OdealClient odealClient) {
 *         this.odealClient = odealClient;
 *     }
 * }
 * }</pre>
 * 
 * <p><strong>Not:</strong> Bu sınıf Spring Boot bağımlılığı gerektirir.
 * Spring Boot kullanmıyorsanız {@code OdealClient}'ı doğrudan oluşturun.</p>
 */
public class OdealAutoConfiguration {

    /**
     * Ortam değişkenlerinden OdealConfig oluşturur.
     * 
     * <p>Aşağıdaki ortam değişkenlerini okur:</p>
     * <ul>
     *   <li>ODEAL_SECRET_KEY</li>
     *   <li>ODEAL_MERCHANT_KEY</li>
     *   <li>ODEAL_BASE_URL (opsiyonel)</li>
     *   <li>ODEAL_ENVIRONMENT (opsiyonel — "staging" veya "production")</li>
     *   <li>ODEAL_SANDBOX (opsiyonel — "true"/"false")</li>
     *   <li>ODEAL_DEBUG (opsiyonel, "true"/"false")</li>
     *   <li>ODEAL_TIMEOUT_MS (opsiyonel)</li>
     *   <li>ODEAL_MAX_RETRY (opsiyonel)</li>
     *   <li>ODEAL_TIMEOUT_MS (opsiyonel)</li>
     *   <li>ODEAL_MAX_RETRY (opsiyonel)</li>
     * </ul>
     * 
     * @return Yapılandırılmış OdealConfig
     * @throws IllegalStateException Zorunlu ortam değişkenleri eksikse
     */
    public static OdealConfig fromEnvironment() {
        String secretKey = System.getenv("ODEAL_SECRET_KEY");
        String merchantKey = System.getenv("ODEAL_MERCHANT_KEY");

        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException("ODEAL_SECRET_KEY environment variable must be set.");
        }
        if (merchantKey == null || merchantKey.isEmpty()) {
            throw new IllegalStateException("ODEAL_MERCHANT_KEY environment variable must be set.");
        }

        OdealConfig config = new OdealConfig();
        config.setSecretKey(secretKey);
        config.setMerchantKey(merchantKey);

        String baseUrl = System.getenv("ODEAL_BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            config.setBaseUrl(baseUrl);
        }

        // Ortam
        String environment = System.getenv("ODEAL_ENVIRONMENT");
        if (environment != null && !environment.isEmpty()) {
            OdealEnvironment env = OdealEnvironment.fromString(environment);
            config.setBaseUrl(env.getBaseUrl());
        }

        // Base URL override (en yüksek öncelik)
        if (baseUrl != null && !baseUrl.isEmpty()) {
            config.setBaseUrl(baseUrl);
        }

        // Sandbox
        String sandbox = System.getenv("ODEAL_SANDBOX");
        if ("true".equalsIgnoreCase(sandbox)) {
            config.setBaseUrl(OdealEnvironment.STAGING.getBaseUrl());
        }

        String debug = System.getenv("ODEAL_DEBUG");
        if ("true".equalsIgnoreCase(debug)) {
            config.setDebugMode(true);
        }

        String timeout = System.getenv("ODEAL_TIMEOUT_MS");
        if (timeout != null) {
            try { config.setTimeoutMs(Integer.parseInt(timeout)); } catch (NumberFormatException ignored) {}
        }

        String maxRetry = System.getenv("ODEAL_MAX_RETRY");
        if (maxRetry != null) {
            try { config.setMaxRetryCount(Integer.parseInt(maxRetry)); } catch (NumberFormatException ignored) {}
        }

        return config;
    }

    /**
     * OdealClient'ı ortam değişkenlerinden oluşturur.
     * 
     * @return Yapılandırılmış OdealClient
     */
    public static OdealClient createFromEnvironment() {
        return new OdealClient(fromEnvironment());
    }
}
