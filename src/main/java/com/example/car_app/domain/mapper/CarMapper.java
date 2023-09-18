package com.example.car_app.domain.mapper;

import com.example.car_app.domain.api.dto.CarDto;
import com.example.car_app.domain.api.request.CarRequest;
import com.example.car_app.infrastructure.database.entity.CarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CarMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "ownershipHistory", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    CarEntity map(CarRequest carRequest);

    CarDto map(CarEntity carEntity);
}
