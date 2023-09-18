package com.example.car_app.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CarAlreadyOwnedException extends RuntimeException {

    public CarAlreadyOwnedException() {
        super(String.format("Car already has an owner!"));
        log.error("Car already has an owner!");
    }
}
