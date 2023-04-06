package com.nhnacademy.gw1.parking.entity;

import com.nhnacademy.gw1.parking.exception.CarNumOver4DigitsException;

public class Car {
    private final int vehicleNumber;
    private final CarType carType;

    private Car(int vehicleNumber, CarType carType) {
        this.vehicleNumber = vehicleNumber;
        this.carType = carType;
    }

    public static Car makeCar(int vehicleNumber, CarType carType) {
        isCarNumOver4Digits(vehicleNumber);
        return new Car(vehicleNumber, carType);
    }

    public int getVehicleNumber() {
        return vehicleNumber;
    }

    public CarType getCarType() {
        return carType;
    }

    private static boolean isCarNumOver4Digits(int vehicleNumber){
        int carNumberDigits = (int) (Math.log10(vehicleNumber) + 1);
        if(carNumberDigits > 4){
            throw new CarNumOver4DigitsException(vehicleNumber);
        }
        return true;
    }
}

