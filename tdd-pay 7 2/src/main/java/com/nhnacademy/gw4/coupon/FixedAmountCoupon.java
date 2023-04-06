package com.nhnacademy.gw4.coupon;

public class FixedAmountCoupon implements Coupon{
    private final long couponAmount;

    public FixedAmountCoupon(long couponAmount) {
        this.couponAmount = couponAmount;
    }

    @Override
    public long discount(long amount) {
        if(amount < couponAmount){
            return 0;
        }
        return amount - couponAmount;
    }
}
