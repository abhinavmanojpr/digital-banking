package com.digitalbanking.exception;

public class ExpiredResetTokenException extends RuntimeException {

    public ExpiredResetTokenException(String message) {
        super(message);
    }
}