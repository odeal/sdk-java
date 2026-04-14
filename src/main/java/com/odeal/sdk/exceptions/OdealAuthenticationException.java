package com.odeal.sdk.exceptions;

/** Kimlik doğrulama hatası (401). */
public class OdealAuthenticationException extends OdealApiException {
    public OdealAuthenticationException(String message, String responseBody) {
        super(message != null ? message : "Authentication failed.", 401, responseBody);
    }
}
