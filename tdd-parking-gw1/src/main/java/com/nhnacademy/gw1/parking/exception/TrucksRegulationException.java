package com.nhnacademy.gw1.parking.exception;

public class TrucksRegulationException extends RuntimeException {
    public TrucksRegulationException() {
        super("Trucks do not enter");
    }
}
