package com.example.car_app.domain.mapper;

import com.example.car_app.domain.api.dto.UserDto;
import com.example.car_app.domain.api.request.UserRequest;
import com.example.car_app.infrastructure.database.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity map(UserRequest userRequest);

    UserDto map(UserEntity userEntity);
}
