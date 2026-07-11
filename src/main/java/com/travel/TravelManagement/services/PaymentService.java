package com.travel.TravelManagement.services;

import com.travel.TravelManagement.AdvancedServices.EmailService;
import com.travel.TravelManagement.model.Booking;
import com.travel.TravelManagement.model.Driver;
import com.travel.TravelManagement.model.Payment;
import com.travel.TravelManagement.model.Registration;
import com.travel.TravelManagement.repo.BookingRepo;
import com.travel.TravelManagement.repo.DriverRepo;
import com.travel.TravelManagement.repo.PaymentRepo;
import com.travel.TravelManagement.repo.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private EmailService emailService;

    public List<Payment> addPayment(Payment payment) {
        Booking bookingid=bookingRepo.findById(payment.getBooking().getId()).orElseThrow(()->new RuntimeException("not found id"));
       payment.setBooking(bookingid);
         paymentRepo.save(payment);
        Registration customer =bookingid.getRegistration();
        String subject = "Payment Successful";

        String body = """
Hello %s,

Your payment has been received successfully.

Payment Details:

Booking ID : %d
Package Name : %s
Amount Paid : ₹%.2f
Payment Method : %s
Payment Date : %s

Thank you for choosing our service.

Travel Management Team
""".formatted(
        customer.getFirstName(),
                bookingid.getId(),
                bookingid.getAPackage().getTitle(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentDate()
        );
//%d - integer
        //%S -string
        //%b- boolean
        //%f -float,double
        emailService.sendEmail(customer.getEmail(),subject,body);
        System.out.println("Payment Email sent Successfully");
         return paymentRepo.findAll();
    }

    public List<Payment> getAllPayments() {
        return paymentRepo.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepo.findById(id).get();
    }

    public List<Payment> updatePaymentById(Long id, Payment payment) {
        Payment oldPayment = paymentRepo.findById(id).get();
        oldPayment.setAmount(payment.getAmount());
        oldPayment.setPaymentMethod(payment.getPaymentMethod());
        oldPayment.setTransactionId(payment.getTransactionId());
         paymentRepo.save(oldPayment);
         return paymentRepo.findAll();
    }

    public List<Payment> updatePaymentStatus(Long id, Payment payment) {
        Payment oldPayment = paymentRepo.findById(id).get();
        oldPayment.setPaymentStatus(payment.getPaymentStatus());
         paymentRepo.save(oldPayment);
         return paymentRepo.findAll();
    }

    public List<Payment> deletePayment(Long id) {
        paymentRepo.deleteById(id);
        return paymentRepo.findAll();
    }

    public Long countPayments() {
        return paymentRepo.count();
    }

    public Long confirmCount() {
        return paymentRepo.countByPaymentStatus("SUCCESS");
    }

    public Long pendingCount() {
        return paymentRepo.countByPaymentStatus("PENDING");
    }

    public Long rejectCount() {
        return paymentRepo.countByPaymentStatus("FAILED");
    }
}
