package com.odeal.sdk.exceptions;

import java.util.List;
import java.util.Collections;

/**
 * Client-side validation hatalarını temsil eder (HTTP isteği gönderilmeden önce).
 */
public class OdealValidationException extends OdealException {
    private final List<String> errors;

    public OdealValidationException(String message, List<String> errors) {
        super(message);
        this.errors = errors != null ? Collections.unmodifiableList(errors) : Collections.emptyList();
    }

    /** Validation hatalarının listesi. */
    public List<String> getErrors() { return errors; }
}
