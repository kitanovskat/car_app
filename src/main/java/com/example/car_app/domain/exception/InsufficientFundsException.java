package com.example.car_app.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
        super(String.format("Not enough money for buying the car"));
        log.error("Not enough money for buying the car");
    }
}
