package com.odeal.sdk.exceptions;

public class OdealApiException extends RuntimeException {
    private final int statusCode;
    private final String responseBody;

    public OdealApiException(String message, int statusCode, String responseBody) {
        super(message);
        this.statusCode = statusCode;
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }
}
