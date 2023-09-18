package com.example.car_app.application.controller;

import com.example.car_app.domain.api.dto.BuyCarRequest;
import com.example.car_app.domain.api.dto.CarDto;
import com.example.car_app.domain.api.request.CarRequest;
import com.example.car_app.domain.service.CarApi;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cars")
@AllArgsConstructor
public class CarController {

    private final CarApi carApi;

    @GetMapping
    public ResponseEntity<List<CarDto>> listCars() {
        List<CarDto> cars = carApi.get();
        return ResponseEntity.ok(cars);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCar(@RequestBody CarRequest carRequest) {
        carApi.save(carRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("Car created successfully");
    }

    @PostMapping("/create-batch")
    public ResponseEntity<String> createCarsBatch(@RequestBody List<CarRequest> carRequests) {
        carApi.saveBatch(carRequests);
        return ResponseEntity.status(HttpStatus.CREATED).body("Batch cars created successfully");
    }

    @PostMapping("/buy-car")
    public ResponseEntity<String> buyCar(@RequestBody BuyCarRequest buyCarRequest) {
        carApi.buyCar(buyCarRequest);
        return ResponseEntity.ok("Car purchased successfully");
    }
}
