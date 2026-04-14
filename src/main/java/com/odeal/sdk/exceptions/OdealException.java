package com.odeal.sdk.exceptions;

/**
 * Odeal SDK'dan fırlatılan tüm exception'ların temel sınıfı.
 */
public class OdealException extends RuntimeException {
    public OdealException(String message) { super(message); }
    public OdealException(String message, Throwable cause) { super(message, cause); }
}
