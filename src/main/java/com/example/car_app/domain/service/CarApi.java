package com.example.car_app.domain.service;

import com.example.car_app.domain.api.dto.BuyCarRequest;
import com.example.car_app.domain.api.dto.CarDto;
import com.example.car_app.domain.api.request.CarRequest;
import java.util.List;

public interface CarApi {

    void save(CarRequest carRequest);

    void saveBatch(List<CarRequest> carRequests);

    List<CarDto> get();

    void buyCar(BuyCarRequest buyCarRequest);

    List<CarDto> getAvailableCars();
}
