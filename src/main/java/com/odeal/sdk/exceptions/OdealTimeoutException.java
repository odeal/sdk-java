package com.odeal.sdk.exceptions;

/** İstek zaman aşımı hatası. */
public class OdealTimeoutException extends OdealNetworkException {
    public OdealTimeoutException(String message) {
        super(message != null ? message : "The request timed out.");
    }
}
