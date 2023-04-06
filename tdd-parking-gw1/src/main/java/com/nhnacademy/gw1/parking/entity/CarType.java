package com.nhnacademy.gw1.parking.entity;

public enum CarType {
    COMPACT_CAR(1),
    MIDSIZE_CAR(2),
    TRUCK(3)
    ;

    private final int carType;

    CarType(int carType) {
        this.carType = carType;
    }

    public int getCarType() {
        return carType;
    }
}
