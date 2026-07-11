package com.travel.TravelManagement.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class PaymentByRazorpay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Booking booking;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private double amount;
    private String paymentMethod;
    private String paymentStatus;
    @CreationTimestamp
    private LocalDateTime createdDate;
}
