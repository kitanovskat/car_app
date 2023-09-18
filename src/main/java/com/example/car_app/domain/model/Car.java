package com.example.car_app.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {

    private Integer id;

    private String name;

    private String category;

    private String brand;

    private String description;

    private Double price;
}

