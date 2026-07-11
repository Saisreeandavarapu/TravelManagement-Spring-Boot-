package com.travel.TravelManagement.DTO;

import lombok.Data;

@Data
public class CustomerDashboardDTO {
    private Long totalBooking;
    private Long completedTrips;
    private Long cancelledTrips;
    private Long wishlistCount;

    private String name;
    private String phone;
    private String email;

}
