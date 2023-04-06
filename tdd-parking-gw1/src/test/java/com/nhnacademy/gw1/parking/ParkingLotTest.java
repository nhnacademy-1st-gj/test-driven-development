package com.nhnacademy.gw1.parking;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import com.nhnacademy.gw1.parking.exception.NoVacantException;
import com.nhnacademy.gw1.parking.exception.TrucksRegulationException;
import com.nhnacademy.gw1.parking.parkinglot.Entrance;
import com.nhnacademy.gw1.parking.parkinglot.Exit;
import com.nhnacademy.gw1.parking.parkinglot.ParkingLot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParkingLotTest {

    ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot();
    }

    @Test
    @DisplayName("자동차 한대가 주차되는지 테스트")
    void parked_a_car_success() {
        Car car = Car.makeCar(1234, CarType.COMPACT_CAR);
        parkingLot.enteringParkingLot(car);
        assertThat(parkingLot.getParkingSpaces().size()).isEqualTo(1);
    }

//    @Test
//    @DisplayName("빈 주차칸이 있는지 확인하는 테스트")
//    void is_vacant_parking_space_success(){
//
//        assertThat(parkingLot.isVacantSpaceLeft()).isTrue();
//
//    }

    @Test
    @DisplayName("주차칸이 전부 찼을 때 에러를 던지는지 테스트")
    void no_vacant_left_test() {
        for (int i = 0; i < 9; i++) {
            Car car = Car.makeCar(1234, CarType.COMPACT_CAR);
            parkingLot.enteringParkingLot(car);
        }
        Car car = Car.makeCar(1235, CarType.COMPACT_CAR);
        parkingLot.enteringParkingLot(car);
        assertThatThrownBy(() -> parkingLot.enteringParkingLot(car))
                .isInstanceOf(NoVacantException.class)
                .hasMessageContaining("There is no empty space in parking lot");
    }

    @Test
    @DisplayName("트럭 주차불가 테스트")
    void no_truck_enter_exception() {
        Car truck = Car.makeCar(1234, CarType.TRUCK);
        assertThatThrownBy(() -> parkingLot.enteringParkingLot(truck))
                .isInstanceOf(TrucksRegulationException.class)
                .hasMessageContaining("Trucks do not enter");
    }

    @Test
    @DisplayName("차량 진입 시 차량의 정보를 스캔하는지 확인")
    void scan_car_num_time() {
        int carNumber = 1234;
        Car car = Car.makeCar(carNumber, CarType.MIDSIZE_CAR);
        Entrance entrance = new Entrance();

        assertThat(entrance.scanCarNumber(car)).isEqualTo(carNumber);
    }

    @Test
    @DisplayName("주차한 차량의 정보가 레포지토리에 들어가는지 확인")
    void car_num_time_into_repo_success() {
        Car car = Car.makeCar(1234, CarType.COMPACT_CAR);
        parkingLot.enteringParkingLot(car);

        assertThat(parkingLot.getCarRepository().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("나가는 차의 시간을 가져오는지 확인")
    void get_leaving_car_time() {
        int carNumber = 1234;
        Car car = Car.makeCar(carNumber, CarType.MIDSIZE_CAR);
        parkingLot.enteringParkingLot(car);

        Exit exit = new Exit();
        LocalDateTime localDateTime = parkingLot.getCarRepository().get(carNumber);
        //System.out.println(localDateTime);

        LocalDateTime leavingCarInfo = exit.getLeavingCarInfo(car);

        Duration duration = Duration.between(localDateTime, leavingCarInfo);

        System.out.println(duration);


        assertThat(duration).isEqualTo(0);

    }


}
