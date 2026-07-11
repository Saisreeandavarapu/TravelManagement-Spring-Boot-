package com.travel.TravelManagement.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class DashboardDTO {

    private Long totalUsers;
    private Long totalDrivers;
    private Long totalPackages;
    private Long totalBookings;
    private Long totalPayments;


    private String firstName;
    private String email;
    private String role;
}
