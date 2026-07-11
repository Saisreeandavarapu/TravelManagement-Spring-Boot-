package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.model.Payment;
import com.travel.TravelManagement.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Payments")
@CrossOrigin("*")
public class PaymentController {
    @Autowired
    private PaymentService service;
    @PostMapping("/add")
    public List<Payment> addPayment(@RequestBody Payment payment){
        return service.addPayment(payment);
    }
    @GetMapping("/all")
    public List<Payment> getAllPayments()
    {
        return service.getAllPayments();
    }
    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable Long id)
    {
        return service.getPaymentById(id);
    }
    @PutMapping("/update/{id}")
    public List<Payment> updatePaymentById(@PathVariable Long id, @RequestBody Payment payment)
    {
        return service.updatePaymentById(id,payment);
    }
    @PutMapping("/status/{id}")
    public List<Payment> updatePaymentStatus(@PathVariable Long id, @RequestBody Payment payment)
    {
        return service.updatePaymentStatus(id,payment);
    }
    @DeleteMapping("/delete/{id}")
    public List<Payment> deletePayment(@PathVariable Long id)
    {
        return service.deletePayment(id);
    }
    @GetMapping("/count")
    public Long countPayments()
    {
        return service.countPayments();
    }
    @GetMapping("/confrimCount")
    public Long confirmCount()
    {
        return service.confirmCount();
    }
    @GetMapping("/pendingCount")
    public Long pendingCount()
    {
        return service.pendingCount();
    }
    @GetMapping ("/rejectCount")
    public Long rejectCount()
    {
        return service.rejectCount();
    }
}
