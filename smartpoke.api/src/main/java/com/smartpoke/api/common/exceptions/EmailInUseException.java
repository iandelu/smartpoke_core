package com.smartpoke.api.common.exceptions;

public class EmailInUseException extends RuntimeException{
    public EmailInUseException() {
        super("Email already in use");
    }

    public EmailInUseException(String message) {
        super(message);
    }
}
