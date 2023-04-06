package com.nhnacademy.gw4;

public class Receipt {
    private final long amount;
    private final Customer customer;
    private long point;
    private long totalSaveMoney;
    public Receipt(long amount, Customer customer) {
        this.amount=amount;
        this.customer=customer;
        this.point=amount/100;
        this.totalSaveMoney=point+customer.getPoint();
    }
    public Long getAmount(){
        return this.amount;
    }
    public Long getPoint(){
        return this.point;
    }

}
