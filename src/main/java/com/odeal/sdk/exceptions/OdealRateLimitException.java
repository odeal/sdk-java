package com.odeal.sdk.exceptions;

/** Rate limit aşıldı (429). */
public class OdealRateLimitException extends OdealApiException {
    private final Integer retryAfterSeconds;

    public OdealRateLimitException(String message, String responseBody, Integer retryAfterSeconds) {
        super(message != null ? message : "Rate limit exceeded.", 429, responseBody);
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public Integer getRetryAfterSeconds() { return retryAfterSeconds; }
}
