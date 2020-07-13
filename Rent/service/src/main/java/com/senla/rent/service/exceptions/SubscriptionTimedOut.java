package com.senla.rent.service.exceptions;

public class SubscriptionTimedOut extends RuntimeException {
    public SubscriptionTimedOut(String message) {
        super(message);
    }
}

