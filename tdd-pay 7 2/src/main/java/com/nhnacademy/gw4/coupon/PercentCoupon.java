package com.nhnacademy.gw4.coupon;

public class PercentCoupon implements Coupon{
    public long discountRate;

    public PercentCoupon(long discountRate){
        this.discountRate = Math.min(discountRate, 100L);
    }
    @Override
    public long discount(long amount) {
        return amount * (100 - this.discountRate) / 100;
    }
}
