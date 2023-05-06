package com.nhnacademy.gwjs.exception;

public class CreateMoneyWithNegativeNumbers extends RuntimeException {
    public CreateMoneyWithNegativeNumbers(double amount) {
        super("Amount must be positive numbers. Current amount: " + amount);
    }
}
