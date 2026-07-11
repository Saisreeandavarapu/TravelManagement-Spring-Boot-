package com.travel.TravelManagement.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class DriverDashboardDTO {

    private Long totalPackages;
    private Long approvedPackages;
    private Long pendingPackages;
    private Long totalBookings;
    private Long completedTrips;
    private Long averageRating;

    private String firstName;
    private String email;
    private String phone;
    private String vehicleName;
    private String vehicleType;
    private Integer experienceYears;
    private String status;
}
