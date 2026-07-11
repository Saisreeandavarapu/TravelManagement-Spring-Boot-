package com.travel.TravelManagement.DTO;

import lombok.Data;

@Data
public class VerifyOtpDTO {
    private String email;
    private String otp;
}
