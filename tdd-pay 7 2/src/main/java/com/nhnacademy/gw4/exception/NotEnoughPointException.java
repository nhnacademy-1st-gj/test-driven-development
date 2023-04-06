package com.nhnacademy.gw4.exception;

public class NotEnoughPointException extends RuntimeException{
    public NotEnoughPointException(long point){
        super("Not Enough Point: " + point);
    }
}
