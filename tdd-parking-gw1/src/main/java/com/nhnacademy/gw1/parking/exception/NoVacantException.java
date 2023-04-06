package com.nhnacademy.gw1.parking.exception;

public class NoVacantException extends RuntimeException{
    public NoVacantException() {
        super("There is no empty space in parking lot");
    }
}
