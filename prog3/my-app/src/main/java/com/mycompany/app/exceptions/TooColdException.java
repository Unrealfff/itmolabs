package com.mycompany.app.exceptions;

public class TooColdException extends RuntimeException {
    public TooColdException (String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}