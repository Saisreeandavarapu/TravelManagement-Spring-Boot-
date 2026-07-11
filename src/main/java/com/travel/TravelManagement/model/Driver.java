package com.travel.TravelManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Registration registration;

    private String licenseNumber;

    private String vehicleName;

    private String vehicleType;

    private Integer experienceYears;

    private String address;

    private String city;

    private String state;

    private String status; // PENDING, APPROVED, REJECTED
    @CreationTimestamp
    private LocalDateTime createdAt;
}
