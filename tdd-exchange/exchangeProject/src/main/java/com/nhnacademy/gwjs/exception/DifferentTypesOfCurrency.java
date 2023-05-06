package com.nhnacademy.gwjs.exception;


import com.nhnacademy.gwjs.entity.Currency;

public class DifferentTypesOfCurrency extends RuntimeException {
    public DifferentTypesOfCurrency(Currency currency, Currency currency2) {
        super("Different types of currency: " + currency + " and " + currency2);
    }
}
