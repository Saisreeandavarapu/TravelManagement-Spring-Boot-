package com.travel.TravelManagement.repo;

import com.travel.TravelManagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
    Long countByPaymentStatus(String status);
}
