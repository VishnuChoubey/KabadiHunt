package com.KabadiHunt.exception;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends EwasteAPIException {
    private Map<String, String> errors;

    public ValidationException(Map<String, String> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public ValidationException(String field, String message) {
        super(message);
        this.errors = new HashMap<>();
        this.errors.put(field, message);
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
