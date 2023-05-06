package com.nhnacademy.gwjs.service;

import com.nhnacademy.gwjs.entity.Currency;
import com.nhnacademy.gwjs.entity.Money;
import com.nhnacademy.gwjs.exception.DifferentTypesOfCurrency;
import com.nhnacademy.gwjs.exception.NegativeNumbersResultException;
import com.nhnacademy.gwjs.exception.SameCurrencyException;

public class Bank {
    public Money createMoney(double amount, Currency currency) {
        return Money.of(amount, currency);
    }

    public Money addUpMoney(Money money1, Money money2) {
        double calculated = money1.getAmount() + money2.getAmount();
        isDifferentCurrency(money1, money2);

        if (money1.getCurrency() == Currency.WON) { //원화라면 소수점을 모두 제거해서 리턴
            return Money.of(Math.floor(calculated), money1.getCurrency());
        }

        double twoDecimalPlaces = Double.parseDouble(String.format("%.2f", calculated)); //원화가 아니라면 소수점 뒤 두자리까지 리턴

        return Money.of(twoDecimalPlaces, money1.getCurrency());
    }

    public Money subtractMoney(Money money1, Money money2) {
        subtractLargerNumFromSmallerNum(money1, money2);

        double calculated = money1.getAmount() - money2.getAmount();
        isDifferentCurrency(money1, money2);

        if (money1.getCurrency() == Currency.WON) { //원화라면 소수점을 모두 제거해서 리턴
            return Money.of(Math.floor(calculated), money1.getCurrency());
        }

        double twoDecimalPlaces = Double.parseDouble(String.format("%.2f", calculated)); //원화가 아니라면 소수점 뒤 두자리까지 리턴


        return Money.of(twoDecimalPlaces, money1.getCurrency());
    }

    public Money exchangeWonToForeignCurrency(Money money, Currency currency) {
        if (currency == Currency.WON) { //원화를 원화로 바꾸려 한다면 SameCurrencyException 발생
            throw new SameCurrencyException(currency);
        }

        double roundMoney = Math.round(calculateExchangeFee(money) / currency.getRate() * 100) / 100.0;

        return Money.of(roundMoney, currency);
    }

    public Money exchangeForeignCurrencyToWon(Money money, Currency currency) {

        double roundMoney = Math.round(calculateExchangeFee(money) * currency.getRate() / 10) * 10.0;

        return Money.of(roundMoney, Currency.WON);
    }

    public double calculateExchangeFee(Money money) {
        double EXCHANGE_FEE = 0.15;
        return money.getAmount() - money.getAmount() * EXCHANGE_FEE;
    }


    private void isDifferentCurrency(Money money1, Money money2) {
        if (money1.getCurrency() != money2.getCurrency()) {
            throw new DifferentTypesOfCurrency(money1.getCurrency(), money2.getCurrency());
        }
    }

    private static void subtractLargerNumFromSmallerNum(Money money1, Money money2) {
        if (money1.getAmount() < money2.getAmount()) {
            throw new NegativeNumbersResultException(money1.getAmount(), money2.getAmount());
        }
    }


}
