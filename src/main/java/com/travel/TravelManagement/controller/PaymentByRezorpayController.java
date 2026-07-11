package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.AdvancedServices.PaymentByRazorpayService;
import com.travel.TravelManagement.model.PaymentByRazorpay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/Payments")
public class PaymentByRezorpayController {
    @Autowired
    private PaymentByRazorpayService paymentByRazorpayService;
    @PostMapping("/createOrder/{bookingId}")
    public String createOrder(@PathVariable Long bookingId ){
       return paymentByRazorpayService.createOrder(bookingId);
    }
}
