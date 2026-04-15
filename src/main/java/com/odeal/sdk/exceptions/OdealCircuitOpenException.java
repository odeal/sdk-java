package com.odeal.sdk.exceptions;

/**
 * Circuit breaker açık olduğunda fırlatılır.
 * Art arda hata alındığında istekler otomatik durdurulur.
 */
public class OdealCircuitOpenException extends OdealException {
    public OdealCircuitOpenException() {
        super("Circuit breaker is open. Requests are temporarily blocked.");
    }

    public OdealCircuitOpenException(String message) {
        super(message);
    }
}
