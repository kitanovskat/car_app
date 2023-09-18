package com.example.car_app.application.controller;

import com.example.car_app.infrastructure.database.entity.CarOwnershipHistoryEntity;
import com.example.car_app.infrastructure.database.repository.CarOwnershipHistoryRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("car-history")
@AllArgsConstructor
public class CarOwnershipHistoryController {

    private final CarOwnershipHistoryRepository carOwnershipHistoryRepository;

    @GetMapping
    public ResponseEntity<List<CarOwnershipHistoryEntity>> getCarOwnershipHistory() {
        List<CarOwnershipHistoryEntity> history = carOwnershipHistoryRepository.findAll();
        return ResponseEntity.ok(history);
    }
}

