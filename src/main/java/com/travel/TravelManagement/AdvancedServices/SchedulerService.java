package com.travel.TravelManagement.AdvancedServices;

import com.travel.TravelManagement.model.Booking;
import com.travel.TravelManagement.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SchedulerService {
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private EmailService emailService;
    @Scheduled(cron = "0 0 8 * * ?")
    private String todayRemainder()
    {
        LocalDate today=LocalDate.now();
        List<Booking> bookings=bookingRepo.todayBookings(today);
        for(Booking booking:bookings)
        {
            String message="""
Hello %s,

Your trip is scheduled for TODAY.

Package : %s

Travel Date : %s

Please arrive at the pickup location on time.

Have a wonderful journey.

Travel Management Team
"""
                    .formatted(booking.getRegistration().getFirstName(),
                            booking.getAPackage().getTitle(),
                            booking.getTravelDate());
            emailService.sendTravelReminder(booking, "Today's Travel Reminder",message);

        }
        return "Email sent sucessfully!";
    }
    @Scheduled(cron = "0 30 8 * * ?")
    private String TomorrowRemainder()
    {
        LocalDate tomorrow=LocalDate.now();
        List<Booking> bookings=bookingRepo.todayBookings(tomorrow);

        for (Booking booking:bookings)
        {
            String message="""
Hello %s,

This is a reminder that your trip is scheduled for TOMORROW.

Package : %s

Travel Date : %s

Please make sure your luggage and travel documents are ready.

Thank you for choosing us.

Travel Management Team
"""
                    .formatted(

                            booking.getRegistration().getFirstName(),

                            booking.getAPackage().getTitle(),

                            booking.getTravelDate()

                    );

            emailService.sendTravelReminder(

                    booking,

                    "Tomorrow Travel Reminder",

                    message

            );
        }
        return "Email sent sucessfully!";
    }
}
