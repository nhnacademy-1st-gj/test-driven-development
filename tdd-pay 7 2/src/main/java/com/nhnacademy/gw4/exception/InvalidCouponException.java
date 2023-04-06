package com.nhnacademy.gw4.exception;

public class InvalidCouponException extends RuntimeException {
    public InvalidCouponException(Long customerId) {
        super("Invalid coupon: "+ customerId);
    }
}
