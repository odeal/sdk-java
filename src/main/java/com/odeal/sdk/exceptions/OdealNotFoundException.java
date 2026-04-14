package com.odeal.sdk.exceptions;

/** Kaynak bulunamadı (404). */
public class OdealNotFoundException extends OdealApiException {
    public OdealNotFoundException(String message, String responseBody) {
        super(message != null ? message : "Resource not found.", 404, responseBody);
    }
}
