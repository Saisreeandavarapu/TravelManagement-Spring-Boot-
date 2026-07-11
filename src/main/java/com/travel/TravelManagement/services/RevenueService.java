package com.travel.TravelManagement.services;

import com.travel.TravelManagement.DTO.RevenueDTO;
import com.travel.TravelManagement.repo.BookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RevenueService {
    @Autowired
    private BookingRepo bookingRepo;

    public RevenueDTO revenueDetails() {
        RevenueDTO revenueDTO = new RevenueDTO();
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now().minusDays(7);
        revenueDTO.setTodayRevenue(bookingRepo.todayRevenue());
        revenueDTO.setWeeklyRevenue(bookingRepo.weeklyRevenue(endDate,startDate));
        LocalDate monthstartDate=LocalDate.now().withDayOfMonth(1);
        LocalDate monthEndDate=LocalDate.now();
        revenueDTO.setMonthlyRevenue(bookingRepo.monthlyRevenue(monthstartDate,monthEndDate));
        LocalDate yearStartDate=LocalDate.now().withDayOfYear(1);
        LocalDate yearEndDate=LocalDate.now();
        revenueDTO.setYearlyRevenue(bookingRepo.yearlyRevenue(yearStartDate,yearEndDate));
        return revenueDTO;
    }
}
