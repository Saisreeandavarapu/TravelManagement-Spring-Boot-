package com.travel.TravelManagement.repo;

import com.travel.TravelManagement.model.PaymentByRazorpay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentByRezorpayRepo extends JpaRepository<PaymentByRazorpay,Long> {
}
