package com.example.car_app.domain.api.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class BuyCarRequest {

    private UUID userId;

    private UUID carId;
}

