package com.invoice.processing.exception;

public class DestinationFormatException extends Exception {

    public DestinationFormatException(String message) {
        super(message);
    }

    public DestinationFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
