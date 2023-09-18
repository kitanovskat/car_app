package com.example.car_app.domain.service.usecase;

import com.example.car_app.domain.api.dto.BuyCarRequest;
import com.example.car_app.domain.api.dto.CarDto;
import com.example.car_app.domain.api.dto.UserDto;
import com.example.car_app.domain.exception.CarAlreadyOwnedException;
import com.example.car_app.domain.exception.InsufficientFundsException;
import com.example.car_app.domain.service.CarApi;
import com.example.car_app.domain.service.SyncCarsApi;
import com.example.car_app.domain.service.UserApi;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class SyncCarsService implements SyncCarsApi {

    private final CarApi carApi;

    private final UserApi userApi;

    @Override
    public void execute() {
        log.info("Cars sync started");

        List<CarDto> availableCars = carApi.getAvailableCars();
        List<UserDto> allUsers = userApi.getAllUsers();

        for (UserDto user : allUsers) {
            if (userCanBuyCar(user, availableCars)) {
                CarDto carToBuy = availableCars.get(0);

                try {
                    buyCarForUser(user, carToBuy);
                } catch (CarAlreadyOwnedException e) {
                    log.info("User {} cannot buy car {}: Car already owned", user.getEmail(), carToBuy.getName());
                } catch (InsufficientFundsException e) {
                    log.info("User {} cannot buy car {}: Insufficient funds", user.getEmail(), carToBuy.getName());
                }
                availableCars.remove(0);
            }
        }

        log.info("Cars sync completed");
    }

    private boolean userCanBuyCar(UserDto user, List<CarDto> availableCars) {
        return user.getMoney() > 0 && !availableCars.isEmpty();
    }

    private void buyCarForUser(UserDto user, CarDto car) {
        BuyCarRequest buyCarRequest = new BuyCarRequest();
        buyCarRequest.setUserId(user.getId());
        buyCarRequest.setCarId(car.getId());

        carApi.buyCar(buyCarRequest);
        log.info("User {} successfully bought car {}", user.getEmail(), car.getName());
    }
}

