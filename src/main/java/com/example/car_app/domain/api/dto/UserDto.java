package com.example.car_app.domain.api.dto;

import com.example.car_app.domain.model.UserRole;
import java.util.UUID;
import lombok.Data;

@Data
public class UserDto {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private Double money;

    private UserRole role;
}

