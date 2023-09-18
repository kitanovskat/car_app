package com.example.car_app.application.config;

import com.example.car_app.domain.mapper.CarMapper;
import com.example.car_app.domain.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UserMapper getUserMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public CarMapper getCarMapper() {
        return Mappers.getMapper(CarMapper.class);
    }
}
