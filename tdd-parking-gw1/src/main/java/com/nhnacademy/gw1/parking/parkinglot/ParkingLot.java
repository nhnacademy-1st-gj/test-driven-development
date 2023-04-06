package com.nhnacademy.gw1.parking.parkinglot;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import com.nhnacademy.gw1.parking.exception.NoVacantException;
import com.nhnacademy.gw1.parking.exception.TrucksRegulationException;
import com.nhnacademy.gw1.parking.repository.MapCarRepository;
import com.nhnacademy.gw1.parking.repository.ParkingRepository;
import com.nhnacademy.gw1.parking.service.ParkingService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private final int TOTAL_PARKING_SPACES = 10;
    private final List<Car> parkingSpaces = new ArrayList<>(TOTAL_PARKING_SPACES);

    ParkingService parkingService = new ParkingService();
    ParkingRepository parkingRepository = new MapCarRepository();
    Entrance entrance = new Entrance();
    Exit exit = new Exit();

    public List<Car> getParkingSpaces() {
        return parkingSpaces;
    }

    public Map<Integer, LocalDateTime> getCarRepository() {
        return parkingRepository.getCarRepository();
    }

    public void enteringParkingLot(Car car) {
        isVacantSpaceLeft();
        isTruck(car);

        int carNumber = entrance.scanCarNumber(car);
        LocalDateTime entryTime = entrance.scanEntryTime();
        parkingRepository.saveEnteringCarInfo(carNumber, entryTime);

        parkingCar(car);
    }


    private void parkingCar(Car car) {
        parkingSpaces.add(car);
    }

    private boolean isVacantSpaceLeft() {
        if (parkingSpaces.size() == TOTAL_PARKING_SPACES) {
            throw new NoVacantException();
        }
        return true;
    }

    private boolean isTruck(Car car) {
        if (car.getCarType() == CarType.TRUCK) {
            throw new TrucksRegulationException();
        }
        return true;
    }
}
