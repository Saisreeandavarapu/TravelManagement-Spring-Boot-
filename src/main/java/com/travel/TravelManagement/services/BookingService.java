package com.travel.TravelManagement.services;

import com.travel.TravelManagement.AdvancedServices.EmailService;
//import com.travel.TravelManagement.AdvancedServices.WhatsAppService;
import com.travel.TravelManagement.model.Booking;
import com.travel.TravelManagement.model.Driver;
import com.travel.TravelManagement.model.Packages;
import com.travel.TravelManagement.model.Registration;
import com.travel.TravelManagement.repo.BookingRepo;
import com.travel.TravelManagement.repo.DriverRepo;
import com.travel.TravelManagement.repo.PackageRepo;
import com.travel.TravelManagement.repo.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepo repo;
    @Autowired
    private RegistrationRepo  registrationRepo;
    @Autowired
    private PackageRepo  packageRepo;
//    @Autowired
//    private WhatsAppService whatsAppService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private DriverRepo driverRepo;

    public List<Booking> addBooking(Booking booking) {
        Registration reg=registrationRepo.findById(booking.getRegistration().getId()).get();
        Packages pack=packageRepo.findById(booking.getAPackage().getId()).get();
        booking.setRegistration(reg);
        booking.setAPackage(pack);
         repo.save(booking);
         Registration customer=booking.getRegistration();
         Packages customerPackage=booking.getAPackage();
        String subject =
                "Booking Created Successfully";

        String body = """
Hello %s,

Your booking has been created successfully.

Booking Details:

Package Name : %s
Travel Date : %s
Number Of Persons : %d
Total Amount : ₹%.2f
Status : %s

Thank you for choosing our service.

Travel Management Team
""".formatted(
        customer.getFirstName(),
                customerPackage.getTitle(),
                booking.getTravelDate(),
                booking.getNumberOfPersons(),
                booking.getTotalAmount(),
                booking.getBookingStatus()
        );
        emailService.sendEmail(customer.getEmail(),subject,body);
        System.out.println("Your booking has been created successfully.");
        Driver driverDetails=driverRepo.findById(booking.getDriver().getId()).orElse(null);

        String driverbody = """
Hello Driver,

A new booking has been assigned to you.

Booking Details

Booking ID : %d
Customer Name : %s %s
Customer Email : %s
Customer Phone : %s

Package Name : %s
Travel Date : %s
Number Of Persons : %d

Please be ready for the trip.

Travel Management Team
""".formatted(
                booking.getId(),
                reg.getFirstName(),
                reg.getLastName(),
                reg.getEmail(),
                reg.getPhoneNumber(),
                pack.getTitle(),
                booking.getTravelDate(),
                booking.getNumberOfPersons()
        );
        emailService.sendEmail(driverDetails.getRegistration().getEmail(),subject,driverbody);
        System.out.println("Driver Email sent successfully.");

         return repo.findAll();
    }

    public List<Booking> getAllBooking() {
        return repo.findAll();
    }

    public Booking getBookingById(Long id) {
        return repo.findById(id).get();
    }

    public List<Booking> updateBooking(Long id, Booking booking) {
        Booking oldBooking=repo.findById(id).get();
        oldBooking.setTravelDate(booking.getTravelDate());
        oldBooking.setNumberOfPersons(booking.getNumberOfPersons());
        oldBooking.setTotalAmount(booking.getTotalAmount());
        repo.save(oldBooking);
        return repo.findAll();
    }

    public List<Booking> deleteBooking(Long id) {
        repo.deleteById(id);
        return repo.findAll();
    }

    public List<Booking> updateBookingStatus(Long id, Booking booking) {
        Booking oldBooking=repo.findById(id).get();
        oldBooking.setBookingStatus(booking.getBookingStatus());
         repo.save(oldBooking);
         Registration customer=oldBooking.getRegistration();
         Packages customerPackage=oldBooking.getAPackage();
//         String message = """
//Hello %s,
//
//Your booking status has been updated.
//
//Package Name : %s
//Travel Date : %s
//Number Of Persons : %d
//Total Amount : ₹%.2f
//Status : %s
//
//Thank you for choosing our service.
//
//Travel Management Team
//"""
//                .formatted(
//                        customer.getFirstName(),
//                       customerPackage.getTitle(),
//                        booking.getTravelDate(),
//                        booking.getNumberOfPersons(),
//                        booking.getTotalAmount(),
//                        booking.getBookingStatus());
//         whatsAppService.sendMessage(customer.getPhoneNumber(),message);
//        System.out.println("WhatsApp Message Sent Successfully");

        String subject =
                "Booking Status Updated";

        String body = """
Hello %s,

Your booking status has been updated.

Booking Details:

Package Name : %s
Travel Date : %s
Number Of Persons : %d
Total Amount : ₹%.2f
Current Status : %s

Thank you for choosing our service.

Travel Management Team
""".formatted(
        customer.getFirstName(),
           customerPackage.getTitle(),
                booking.getTravelDate(),
                booking.getNumberOfPersons(),
                booking.getTotalAmount(),
                booking.getBookingStatus()

        );

        emailService.sendEmail(customer.getEmail(),subject,body);
        System.out.println("Email Message Updated Successfully");
         return repo.findAll();
    }

    public Long countBooking() {
        return repo.count();
    }

    public List<Booking> getBookindByCustomer(Long id) {
        return repo.findByregistrationId(id);
    }

    public List<Booking> getBookingByPackage(Long id) {
        return repo.findByaPackageId(id);
    }

    public List<Booking> addAllBookings(List<Booking> bookings) {
        return repo.saveAll(bookings);
    }
}
