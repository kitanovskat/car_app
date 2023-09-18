package com.example.car_app.infrastructure.database.repository;

import com.example.car_app.infrastructure.database.entity.CarEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, UUID> {

    List<CarEntity> findByUserIsNull();
}

