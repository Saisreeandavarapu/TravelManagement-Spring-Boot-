package com.travel.TravelManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Packages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Driver driver;

    private String title;

    @Column(length = 5000)
    private String description;

    private Double originalPrice;
    private Double offerPrice;
    private Double discountPercentage;

    private Integer durationDays;

    private Integer maxPeople;

    private String status; // PENDING, APPROVED, REJECTED
    @CreationTimestamp
    private LocalDateTime createdAt;
}
