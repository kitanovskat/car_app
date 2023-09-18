package com.example.car_app.domain.api.request;

import com.example.car_app.domain.model.CarBrand;
import com.example.car_app.domain.model.CarCategory;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class CarRequest {

    private UUID userId;

    private String name;

    private CarCategory category;

    private CarBrand brand;

    private String description;

    private Date manufacturingDate;
}
