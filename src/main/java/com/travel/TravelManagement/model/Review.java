package com.travel.TravelManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Registration registration;

    @ManyToOne
    private Packages aPackage;

    private Integer rating;

    @Column(length = 1000)
    private String comment;
    @CreationTimestamp
    private LocalDateTime reviewDate;
}
