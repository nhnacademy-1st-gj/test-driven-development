package com.nhnacademy.gwjs.exception;

import com.nhnacademy.gwjs.entity.Currency;

public class SameCurrencyException extends RuntimeException {
    public SameCurrencyException(Currency currency) {
        super("Same currency. Cannot be exchanged : " + currency);
    }
}
