package com.nhnacademy.gw4.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long customerId) {
        super("Not found customer: " + customerId);
    }
}
