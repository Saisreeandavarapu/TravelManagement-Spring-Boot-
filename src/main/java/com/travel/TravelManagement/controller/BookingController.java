package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.model.Booking;
import com.travel.TravelManagement.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Booking")
@CrossOrigin("*")
public class BookingController {
    @Autowired
    private BookingService service;
    @PostMapping("/add")
    public List<Booking> addBooking(@RequestBody Booking booking) {
        return service.addBooking(booking);
    }
    @GetMapping("/all")
    public List<Booking> getAllBooking(){
        return service.getAllBooking();
    }
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id){
        return service.getBookingById(id);
    }
    @PutMapping("/update/{id}")
    public List<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return service.updateBooking(id,booking);
    }
    @DeleteMapping("/delete/{id}")
    public List<Booking> deleteBooking(@PathVariable Long id){
        return service.deleteBooking(id);
    }
    @PutMapping("/status/{id}")//driver //admin
    public List<Booking> updateBookingStatus(@PathVariable Long id, @RequestBody Booking booking) {
        return service.updateBookingStatus(id,booking);
    }
    @GetMapping("/count")
    public Long countBooking(){
        return service.countBooking();
    }
    @GetMapping("/customer/{id}")
    public List<Booking> getBookingByCustomer(@PathVariable Long id){
        return service.getBookindByCustomer(id);
    }
    @GetMapping("/package/{id}")
    public List<Booking> getBookingByPackage(@PathVariable Long id){
    return service.getBookingByPackage(id);
    }
}
