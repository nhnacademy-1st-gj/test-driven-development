package com.nhnacademy.gw1.parking.exception;

public class CarNumOver4DigitsException extends RuntimeException {
    public CarNumOver4DigitsException(int vehicleNumber) {
        super("Please enter 4 digits of car number. Current car number: " + vehicleNumber);
    }
}
