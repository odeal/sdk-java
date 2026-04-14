package com.odeal.sdk;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * API bağlantı durumunu kontrol eder.
 *
 * <pre>{@code
 * OdealHealthCheck healthCheck = new OdealHealthCheck(config);
 * HealthCheckResult result = healthCheck.ping();
 * System.out.println("API: " + (result.isHealthy() ? "✓" : "✗") + " (" + result.getLatencyMs() + "ms)");
 * }</pre>
 */
public class OdealHealthCheck {
    private final OdealConfig config;

    public OdealHealthCheck(OdealConfig config) {
        this.config = config;
    }

    /**
     * API'ye basit bir GET isteği göndererek bağlantıyı test eder.
     */
    public HealthCheckResult ping() {
        long start = System.currentTimeMillis();
        try {
            URL url = new URL(config.getBaseUrl() + "/health");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("X-ODEAL-SECRET-KEY", config.getSecretKey());

            int statusCode = conn.getResponseCode();
            long latencyMs = System.currentTimeMillis() - start;
            conn.disconnect();

            return new HealthCheckResult(
                statusCode >= 200 && statusCode < 300,
                statusCode,
                latencyMs,
                config.getBaseUrl(),
                null
            );
        } catch (Exception e) {
            long latencyMs = System.currentTimeMillis() - start;
            return new HealthCheckResult(
                false,
                0,
                latencyMs,
                config.getBaseUrl(),
                e.getMessage()
            );
        }
    }

    /**
     * Health check sonucu.
     */
    public static class HealthCheckResult {
        private final boolean healthy;
        private final int statusCode;
        private final long latencyMs;
        private final String baseUrl;
        private final String errorMessage;

        public HealthCheckResult(boolean healthy, int statusCode, long latencyMs, String baseUrl, String errorMessage) {
            this.healthy = healthy;
            this.statusCode = statusCode;
            this.latencyMs = latencyMs;
            this.baseUrl = baseUrl;
            this.errorMessage = errorMessage;
        }

        public boolean isHealthy() { return healthy; }
        public int getStatusCode() { return statusCode; }
        public long getLatencyMs() { return latencyMs; }
        public String getBaseUrl() { return baseUrl; }
        public String getErrorMessage() { return errorMessage; }

        @Override
        public String toString() {
            if (healthy) return "✓ Healthy (" + latencyMs + "ms) " + baseUrl;
            return "✗ Unhealthy (" + latencyMs + "ms) " + baseUrl + " — " +
                (errorMessage != null ? errorMessage : "HTTP " + statusCode);
        }
    }
}
