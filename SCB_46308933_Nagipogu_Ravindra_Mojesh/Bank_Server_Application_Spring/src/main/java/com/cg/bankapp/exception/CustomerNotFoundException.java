package com.cg.bankapp.exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        return getMessage();
    }
}
