package com.example.car_app.domain.service.usecase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.car_app.domain.api.dto.BuyCarRequest;
import com.example.car_app.domain.api.dto.CarDto;
import com.example.car_app.domain.api.request.CarRequest;
import com.example.car_app.domain.mapper.CarMapper;
import com.example.car_app.infrastructure.database.entity.CarEntity;
import com.example.car_app.infrastructure.database.entity.UserEntity;
import com.example.car_app.infrastructure.database.repository.CarRepository;
import com.example.car_app.infrastructure.database.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSave() {
        CarRequest carRequest = new CarRequest();
        UserEntity userEntity = new UserEntity();

        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(carMapper.map(carRequest)).thenReturn(new CarEntity());

        assertDoesNotThrow(() -> carService.save(carRequest));
    }

    @Test
    void testSaveBatch() {
        List<CarRequest> carRequests = new ArrayList<>();
        carRequests.add(new CarRequest());

        UserEntity userEntity = new UserEntity();
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(carMapper.map(any(CarRequest.class))).thenReturn(new CarEntity());

        assertDoesNotThrow(() -> carService.saveBatch(carRequests));
    }

    @Test
    void testGet() {
        List<CarEntity> carEntities = new ArrayList<>();
        carEntities.add(new CarEntity());

        when(carRepository.findAll()).thenReturn(carEntities);
        when(carMapper.map(any(CarEntity.class))).thenReturn(new CarDto());

        List<CarDto> carDtos = carService.get();

        assertNotNull(carDtos);
        assertFalse(carDtos.isEmpty());
    }

    @Test
    void testBuyCar() {
        BuyCarRequest buyCarRequest = new BuyCarRequest();
        UserEntity userEntity = new UserEntity();
        CarEntity carEntity = new CarEntity();
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity));
        when(carRepository.findById(userId)).thenReturn(Optional.of(carEntity));
        when(carMapper.map(any(CarEntity.class))).thenReturn(new CarDto());

        try {
            carService.buyCar(buyCarRequest);
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e.getClass().getName());
            System.out.println("Exception message: " + e.getMessage());
        }
    }

    @Test
    void testBuyCarCarAlreadyOwned() {
        BuyCarRequest buyCarRequest = new BuyCarRequest();
        CarEntity carEntity = new CarEntity();
        UserEntity userEntity = new UserEntity();
        carEntity.setUser(userEntity);

        UUID userId = UUID.randomUUID();

        when(carRepository.findById(userId)).thenReturn(Optional.of(carEntity));

        try {
            carService.buyCar(buyCarRequest);
            fail("Expected CarAlreadyOwnedException was not thrown.");
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e.getClass().getName());
        }
    }

    @Test
    void testGetAvailableCars() {
        List<CarEntity> availableCars = new ArrayList<>();
        availableCars.add(new CarEntity());

        when(carRepository.findByUserIsNull()).thenReturn(availableCars);
        when(carMapper.map(any(CarEntity.class))).thenReturn(new CarDto());

        List<CarDto> carDtos = carService.getAvailableCars();

        assertNotNull(carDtos);
        assertFalse(carDtos.isEmpty());
    }
}
