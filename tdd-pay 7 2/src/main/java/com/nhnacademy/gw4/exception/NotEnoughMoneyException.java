package com.nhnacademy.gw4.exception;

public class NotEnoughMoneyException extends RuntimeException  {
    public NotEnoughMoneyException(long customerMoney, long amount) {
        super(String.format("Not enough money / CustomerMoney: %d,Amount: %d",customerMoney,amount));
    }
}
