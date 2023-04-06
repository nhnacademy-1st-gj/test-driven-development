package com.nhnacademy.gw1.parking.parkinglot;

import com.nhnacademy.gw1.parking.entity.Car;

import java.time.LocalDateTime;

public class Entrance {

    public int scanCarNumber(Car car) {
        return car.getVehicleNumber();
    }

    public LocalDateTime scanEntryTime() {
        LocalDateTime enteringTime = LocalDateTime.now();
        return LocalDateTime.of(enteringTime.getYear(),
                enteringTime.getMonth(),
                enteringTime.getDayOfMonth(),
                enteringTime.getHour(),
                enteringTime.getMinute(),
                enteringTime.getSecond());
    }
}
