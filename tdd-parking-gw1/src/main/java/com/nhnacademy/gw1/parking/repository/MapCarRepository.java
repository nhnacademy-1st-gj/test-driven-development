package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.Car;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MapCarRepository implements ParkingRepository {
    private final int PARKING_SPACES = 10;
    Map<Integer, LocalDateTime> carMap = new HashMap<>(PARKING_SPACES);

    public Map<Integer, LocalDateTime> getCarRepository() {
        return carMap;
    }

    @Override
    public void saveEnteringCarInfo(Integer carNum, LocalDateTime enteringTime) {
        carMap.put(carNum, enteringTime);
    }

    @Override
    public LocalDateTime getLeavingCarTime(Car car) {
        return carMap.get(car.getVehicleNumber());
    }
}
