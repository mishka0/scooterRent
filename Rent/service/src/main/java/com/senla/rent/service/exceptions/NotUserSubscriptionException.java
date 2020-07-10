package com.senla.rent.service.exceptions;

public class NotUserSubscriptionException extends RuntimeException {
    public NotUserSubscriptionException(String message) {
        super(message);
    }
}
