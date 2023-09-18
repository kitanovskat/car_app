package com.example.car_app.domain.service;

import com.example.car_app.domain.api.dto.UserDto;
import com.example.car_app.domain.api.request.UserRequest;
import java.util.List;

public interface UserApi {

    void save(UserRequest userRequest);

    void saveBatch(List<UserRequest> userRequests);

    List<UserDto> getAllUsers();
}
