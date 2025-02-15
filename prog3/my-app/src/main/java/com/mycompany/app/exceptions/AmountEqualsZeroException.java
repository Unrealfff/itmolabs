package com.mycompany.app.exceptions;

public class AmountEqualsZeroException extends RuntimeException {
    public AmountEqualsZeroException (String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}