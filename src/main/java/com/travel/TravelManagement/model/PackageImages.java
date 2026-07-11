package com.travel.TravelManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PackageImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Packages aPackage;

    private String imageUrl;
}
