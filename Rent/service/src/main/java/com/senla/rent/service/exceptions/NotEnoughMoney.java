package com.senla.rent.service.exceptions;

public class NotEnoughMoney extends RuntimeException {
    public NotEnoughMoney(String message) {
        super(message);
    }
}
