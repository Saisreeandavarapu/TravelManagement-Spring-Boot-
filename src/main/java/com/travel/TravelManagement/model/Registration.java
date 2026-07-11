package com.travel.TravelManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String password;

    private String phoneNumber;

    private String role; // ADMIN, CUSTOMER, DRIVER

    //private String status; // ACTIVE, BLOCKED
    @CreationTimestamp
    private LocalDateTime createdAt;



}
