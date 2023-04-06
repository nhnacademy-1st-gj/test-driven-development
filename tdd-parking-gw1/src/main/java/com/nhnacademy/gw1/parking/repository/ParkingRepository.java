package com.nhnacademy.gw1.parking.repository;

import com.nhnacademy.gw1.parking.entity.Car;

import java.time.LocalDateTime;
import java.util.Map;

public interface ParkingRepository {
    public Map<Integer, LocalDateTime> getCarRepository();

    public void saveEnteringCarInfo(Integer carNum, LocalDateTime enteringTime);

    public LocalDateTime getLeavingCarTime(Car car);


}
