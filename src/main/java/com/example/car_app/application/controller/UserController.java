package com.example.car_app.application.controller;

import com.example.car_app.domain.api.dto.UserDto;
import com.example.car_app.domain.api.request.UserRequest;
import com.example.car_app.domain.service.UserApi;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@AllArgsConstructor
public class UserController {

    private final UserApi userApi;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userApi.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userApi.save(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PostMapping("/create-batch")
    public ResponseEntity<String> createUsersBatch(@RequestBody List<UserRequest> userRequests) {
        userApi.saveBatch(userRequests);
        return ResponseEntity.status(HttpStatus.CREATED).body("Batch users created successfully");
    }
}
