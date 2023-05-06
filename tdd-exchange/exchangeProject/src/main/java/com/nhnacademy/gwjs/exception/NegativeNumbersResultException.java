package com.nhnacademy.gwjs.exception;

public class NegativeNumbersResultException extends RuntimeException {
    public NegativeNumbersResultException(double amount, double amount1) {
        super("The result of the operation is negative : " + (amount - amount1));
    }
}
