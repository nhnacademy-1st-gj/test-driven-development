package com.nhnacademy.gw1.parking.parkinglot;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.repository.MapCarRepository;
import com.nhnacademy.gw1.parking.repository.ParkingRepository;

import java.time.LocalDateTime;

public class Exit {

    public LocalDateTime getLeavingCarInfo(Car car) {
        ParkingRepository parkingRepository = new MapCarRepository();
        return parkingRepository.getLeavingCarTime(car);
    }

    //요금 계산하는 애 불러
}
