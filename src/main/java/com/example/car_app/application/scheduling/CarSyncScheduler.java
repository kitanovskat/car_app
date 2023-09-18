package com.example.car_app.application.scheduling;

import com.example.car_app.domain.service.SyncCarsApi;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class CarSyncScheduler {

    private final SyncCarsApi syncCarsApi;

    @Scheduled(cron = "${cars.sync.cron}")
    public void syncCars() {
        log.info("Scheduler for cars syncing started");
        syncCarsApi.execute();
    }
}
