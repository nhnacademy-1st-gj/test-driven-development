package com.nhnacademy.gw4.exception;

public class InvalidAmountException extends RuntimeException{
    public InvalidAmountException(long amount){
        super("Invalid Amount: " + amount);
    }
}
