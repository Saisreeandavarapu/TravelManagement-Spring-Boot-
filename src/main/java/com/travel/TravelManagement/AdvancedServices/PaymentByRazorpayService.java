package com.travel.TravelManagement.AdvancedServices;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.travel.TravelManagement.model.Booking;
import com.travel.TravelManagement.model.PaymentByRazorpay;
import com.travel.TravelManagement.repo.BookingRepo;
import com.travel.TravelManagement.repo.PaymentByRezorpayRepo;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

@Service
public class PaymentByRazorpayService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private PaymentByRezorpayRepo paymentByRezorpayRepo;
    @Autowired
    private EmailService emailService;
    @SneakyThrows
    public String createOrder(Long bookingId) {
        Booking booking=bookingRepo.findById(bookingId).orElse(null);
        RazorpayClient razorpay=new RazorpayClient("rzp_test_T5OB2v802dYJ8x","rmyH5OC3YAkcWnElj6MJYJ9c");
        JSONObject orderRequest=new JSONObject();
        orderRequest.put("amount",booking.getTotalAmount()*100);
        orderRequest.put("currency","INR");
        orderRequest.put("receipt","booking_"+booking.getId());
        Order order=razorpay.orders.create(orderRequest);
        PaymentByRazorpay payment=new PaymentByRazorpay();
       payment.setBooking(booking);
       payment.setRazorpayOrderId(order.get("razorpay_order_id"));
       payment.setAmount(booking.getTotalAmount());
       payment.setPaymentStatus("CREATED");
       payment.setPaymentMethod(order.get("payment_method"));
       payment.setRazorpayOrderId(order.get("razorpay_order_id"));
       paymentByRezorpayRepo.save(payment);
        String subject =
                "Payment Successful";

        String body = """
Hello %s,

Your payment has been received.

Booking ID : %d
Package : %s
Amount : ₹%.2f

Current Status : %s

Thank You.
""".formatted(
        booking.getRegistration().getFirstName(),
                booking.getId(),
                booking.getAPackage().getTitle(),
                booking.getTotalAmount(),
                booking.getBookingStatus()
        );
        emailService.sendEmail(booking.getRegistration().getEmail(), subject, body);
        return order.toString();
    }
}
