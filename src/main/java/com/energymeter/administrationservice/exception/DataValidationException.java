package com.energymeter.administrationservice.exception;

public class DataValidationException extends RuntimeException {
    public DataValidationException(String message) {
        super(message);
    }
}
