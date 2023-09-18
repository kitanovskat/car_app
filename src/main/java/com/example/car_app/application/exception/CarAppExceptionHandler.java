package com.example.car_app.application.exception;

import com.example.car_app.domain.exception.CarAlreadyOwnedException;
import com.example.car_app.domain.exception.EntityNotFoundException;
import com.example.car_app.domain.exception.InsufficientFundsException;
import com.example.car_app.domain.exception.UserDoesNotExistException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CarAppExceptionHandler {

    @ExceptionHandler({UserDoesNotExistException.class})
    public ResponseEntity<Object> handleUserDoesNotExistException(UserDoesNotExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InsufficientFundsException.class})
    public ResponseEntity<Object> handleInsufficientFundsException(InsufficientFundsException ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({CarAlreadyOwnedException.class})
    public ResponseEntity<Object> handleCarAlreadyOwnedException(CarAlreadyOwnedException ex) {
        return new ResponseEntity<>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
