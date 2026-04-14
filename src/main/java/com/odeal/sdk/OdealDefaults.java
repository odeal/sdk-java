package com.odeal.sdk;

/**
 * SDK varsayılan değerleri ve HTTP durum kodu sabitleri.
 */
public final class OdealDefaults {

    private OdealDefaults() {} // utility class

    // --- Timeout & Retry ---
    public static final int TIMEOUT_MS = 30000;
    public static final int MAX_RETRY_COUNT = 3;
    public static final int BACKOFF_BASE_MS = 500;

    // --- Circuit Breaker ---
    public static final int CIRCUIT_BREAKER_THRESHOLD = 5;
    public static final int CIRCUIT_BREAKER_RESET_MS = 60000;

    // --- HTTP Status Codes ---
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_TOO_MANY_REQUESTS = 429;

    public static boolean isServerError(int statusCode) {
        return statusCode >= 500;
    }

    public static boolean isRetryable(int statusCode) {
        return isServerError(statusCode) || statusCode == HTTP_TOO_MANY_REQUESTS;
    }
}
