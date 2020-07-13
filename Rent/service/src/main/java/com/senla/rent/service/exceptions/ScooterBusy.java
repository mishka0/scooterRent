package com.senla.rent.service.exceptions;

public class ScooterBusy extends RuntimeException {
    public ScooterBusy(String message) {
        super(message);
    }
}
