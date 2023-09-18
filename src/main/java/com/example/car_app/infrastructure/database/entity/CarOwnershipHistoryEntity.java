package com.example.car_app.infrastructure.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name = "car_ownership_history")
public class CarOwnershipHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity car;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp;

    public CarOwnershipHistoryEntity() {
    }

    public CarOwnershipHistoryEntity(CarEntity car, UserEntity user) {
        this.car = car;
        this.user = user;
        this.timestamp = new Date();
    }
}

