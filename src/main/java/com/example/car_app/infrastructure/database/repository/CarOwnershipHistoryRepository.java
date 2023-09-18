package com.example.car_app.infrastructure.database.repository;

import com.example.car_app.infrastructure.database.entity.CarOwnershipHistoryEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarOwnershipHistoryRepository extends JpaRepository<CarOwnershipHistoryEntity, UUID> {

}

