package com.nhnacademy.gw1.parking;

import com.nhnacademy.gw1.parking.entity.Car;
import com.nhnacademy.gw1.parking.entity.CarType;
import com.nhnacademy.gw1.parking.exception.CarNumOver4DigitsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CarTest {

    @Test
    @DisplayName("자동차 만들기 테스트")
    void setUpNewCar() {
        Car car = Car.makeCar(1234, CarType.COMPACT_CAR);
        assertThat(car).isInstanceOf(Car.class);
    }

    @Test
    @DisplayName("4자리 초과하는 번호로 자동차 만들기 실패")
    void car_num_over_4_digit_exception(){

        assertThatThrownBy(() -> Car.makeCar(12345, CarType.COMPACT_CAR))
                .isInstanceOf(CarNumOver4DigitsException.class)
                .hasMessageContaining("Please enter 4 digits of car number. Current car number: ");
    }

}
