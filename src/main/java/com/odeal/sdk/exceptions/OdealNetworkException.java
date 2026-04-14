package com.odeal.sdk.exceptions;

/** Ağ bağlantı hatası. */
public class OdealNetworkException extends OdealException {
    public OdealNetworkException(String message) { super(message); }
    public OdealNetworkException(String message, Throwable cause) { super(message, cause); }
}
