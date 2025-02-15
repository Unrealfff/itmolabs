package com.mycompany.app.exceptions;

public class TooHotException extends RuntimeException {
    public TooHotException (String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}