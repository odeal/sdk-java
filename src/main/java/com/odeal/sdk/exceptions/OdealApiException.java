package com.odeal.sdk.exceptions;

/**
 * API isteklerinde oluşan hataları temsil eder.
 */
public class OdealApiException extends OdealException {
    private final int statusCode;
    private final String responseBody;

    public OdealApiException(String message, int statusCode, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() { return statusCode; }
    public String getResponseBody() { return responseBody; }

    /**
     * HTTP status koduna göre uygun exception alt sınıfını oluşturur.
     */
    public static OdealApiException fromStatusCode(int statusCode, String message, String responseBody) {
        return switch (statusCode) {
            case 401 -> new OdealAuthenticationException(message, responseBody);
            case 403 -> new OdealForbiddenException(message, responseBody);
            case 404 -> new OdealNotFoundException(message, responseBody);
            case 429 -> new OdealRateLimitException(message, responseBody, null);
            default -> new OdealApiException(message, statusCode, responseBody);
        };
    }
}
