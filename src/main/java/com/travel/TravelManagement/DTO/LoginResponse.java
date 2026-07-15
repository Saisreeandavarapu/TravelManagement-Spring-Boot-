package com.travel.TravelManagement.DTO;

import lombok.Data;

@Data
public class LoginResponse {
    private Long id;
    private String email;
    private String password;
    private String role;
    private String token;
}
