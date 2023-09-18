package com.example.car_app.infrastructure.database.entity;

import com.example.car_app.domain.model.CarBrand;
import com.example.car_app.domain.model.CarCategory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private CarCategory category;

    @Enumerated(EnumType.STRING)
    private CarBrand brand;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date manufacturingDate;

    private Double price;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CarOwnershipHistoryEntity> ownershipHistory = new ArrayList<>();

    @PrePersist
    public void calculatePrice() {
        this.price = brand.calculatePrice(manufacturingDate);
    }

    public void transferOwnership(UserEntity newOwner) {
        if (this.user != null) {
            ownershipHistory.add(new CarOwnershipHistoryEntity(this, this.user));
        }
        this.user = newOwner;
    }
}


