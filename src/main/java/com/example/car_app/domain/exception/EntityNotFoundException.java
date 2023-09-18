package com.example.car_app.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String userId) {
        super(String.format("User with id %s doesn't exist", userId));
        log.error("User with name {} doesn't exist", userId);
    }
}
