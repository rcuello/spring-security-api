package com.tecno.api_sec.exceptions;

public class InvalidPasswordException extends IllegalArgumentException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
