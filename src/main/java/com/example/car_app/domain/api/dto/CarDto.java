package com.example.car_app.domain.api.dto;

import com.example.car_app.domain.model.CarBrand;
import com.example.car_app.domain.model.CarCategory;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class CarDto {

    private UUID id;

    private String name;

    private CarCategory category;

    private CarBrand brand;

    private String description;

    private Date manufacturingDate;

    private Double price;
}

