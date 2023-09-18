package com.example.car_app.domain.service.usecase;

import com.example.car_app.domain.api.dto.UserDto;
import com.example.car_app.domain.api.request.UserRequest;
import com.example.car_app.domain.mapper.UserMapper;
import com.example.car_app.domain.service.UserApi;
import com.example.car_app.infrastructure.database.entity.UserEntity;
import com.example.car_app.infrastructure.database.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserApi {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public void save(UserRequest userRequest) {
        UserEntity userEntity = userMapper.map(userRequest);
        userRepository.save(userEntity);
    }

    @Override
    public void saveBatch(List<UserRequest> userRequests) {
        List<UserEntity> userEntities = userRequests.stream()
            .map(userMapper::map)
            .collect(Collectors.toList());

        userRepository.saveAll(userEntities);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream()
            .map(userMapper::map)
            .collect(Collectors.toList());
    }
}
