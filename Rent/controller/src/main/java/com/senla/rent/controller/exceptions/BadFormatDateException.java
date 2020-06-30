package com.senla.rent.controller.exceptions;

@Deprecated
public class BadFormatDateException extends RuntimeException {
    public BadFormatDateException(String message) {
        super(message);
    }
}
