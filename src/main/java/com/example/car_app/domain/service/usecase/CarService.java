package com.example.car_app.domain.service.usecase;

import com.example.car_app.domain.api.dto.BuyCarRequest;
import com.example.car_app.domain.api.dto.CarDto;
import com.example.car_app.domain.api.request.CarRequest;
import com.example.car_app.domain.exception.CarAlreadyOwnedException;
import com.example.car_app.domain.exception.EntityNotFoundException;
import com.example.car_app.domain.exception.InsufficientFundsException;
import com.example.car_app.domain.exception.UserDoesNotExistException;
import com.example.car_app.domain.mapper.CarMapper;
import com.example.car_app.domain.service.CarApi;
import com.example.car_app.infrastructure.database.entity.CarEntity;
import com.example.car_app.infrastructure.database.entity.UserEntity;
import com.example.car_app.infrastructure.database.repository.CarRepository;
import com.example.car_app.infrastructure.database.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CarService implements CarApi {

    private final UserRepository userRepository;

    private final CarRepository carRepository;

    private final CarMapper carMapper;

    @Override
    public void save(CarRequest carRequest) {
        CarEntity carEntity = carMapper.map(carRequest);

        if (carRequest.getUserId() != null) {
            UserEntity adminUser = userRepository.findById(carRequest.getUserId())
                .orElseThrow(() -> new UserDoesNotExistException("User not found"));

            carEntity.setUser(adminUser);
        }

        carRepository.save(carEntity);
    }

    @Override
    public void saveBatch(List<CarRequest> carRequests) {
        List<CarEntity> carEntities = carRequests.stream()
            .map(carMapper::map)
            .collect(Collectors.toList());

        for (CarEntity carEntity : carEntities) {
            if (carEntity.getUser() != null) {
                UserEntity adminUser = userRepository.findById(carEntity.getUser().getId())
                    .orElseThrow(() -> new UserDoesNotExistException("User not found"));
                carEntity.setUser(adminUser);
            }
        }

        carRepository.saveAll(carEntities);
    }

    @Override
    public List<CarDto> get() {
        List<CarEntity> cars = carRepository.findAll();
        return cars.stream()
            .map(carMapper::map)
            .collect(Collectors.toList());
    }

    public void buyCar(BuyCarRequest buyCarRequest) {
        UserEntity user = userRepository.findById(buyCarRequest.getUserId())
            .orElseThrow(() -> new EntityNotFoundException("User not found"));
        CarEntity car = carRepository.findById(buyCarRequest.getCarId())
            .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        if (car.getUser() != null) {
            throw new CarAlreadyOwnedException();
        }

        if (user.getMoney() < car.getPrice()) {
            throw new InsufficientFundsException();
        }

        car.transferOwnership(user);

        carRepository.save(car);
        userRepository.save(user);

        log.info("Car successfully purchased by user {} - Car: {}", user.getEmail(), car.getName());
    }

    @Override
    public List<CarDto> getAvailableCars() {
        List<CarEntity> availableCars = carRepository.findByUserIsNull();
        return availableCars.stream()
            .map(carMapper::map)
            .collect(Collectors.toList());
    }
}
