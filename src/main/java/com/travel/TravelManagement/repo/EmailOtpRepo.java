package com.travel.TravelManagement.repo;

import com.travel.TravelManagement.model.EmailOtp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailOtpRepo  extends JpaRepository<EmailOtp,Long> {
    EmailOtp findByEmail(String email);
}
