package com.odeal.sdk.exceptions;

/** Yetkilendirme hatası (403). */
public class OdealForbiddenException extends OdealApiException {
    public OdealForbiddenException(String message, String responseBody) {
        super(message != null ? message : "Access denied.", 403, responseBody);
    }
}
