package com.travel.TravelManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Booking booking;

    private Double amount;

    private String paymentMethod;

    private String paymentStatus;// PENDING, Success, REJECTED

    private String transactionId;
    @CreationTimestamp
    private LocalDateTime paymentDate;
}
