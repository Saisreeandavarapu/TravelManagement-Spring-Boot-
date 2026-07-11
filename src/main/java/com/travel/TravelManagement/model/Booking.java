package com.travel.TravelManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Registration registration;

    @ManyToOne
    private Packages aPackage;
    @ManyToOne
    private Driver driver;
    @CreationTimestamp
    private LocalDate bookingDate;

    private LocalDate travelDate;

    private Integer numberOfPersons;

    private Double totalAmount;

    private String bookingStatus;
   // PENDING
          //  CONFIRMED
  //  CANCELLED
           // COMPLETED
    @CreationTimestamp
    private LocalDateTime createdAt;
}
