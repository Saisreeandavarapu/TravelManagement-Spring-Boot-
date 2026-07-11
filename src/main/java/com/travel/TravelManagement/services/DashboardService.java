package com.travel.TravelManagement.services;

import com.travel.TravelManagement.DTO.CustomerDashboardDTO;
import com.travel.TravelManagement.DTO.DashboardDTO;
import com.travel.TravelManagement.DTO.DriverDashboardDTO;
import com.travel.TravelManagement.model.*;
import com.travel.TravelManagement.repo.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired
    private RegistrationRepo registrationRepo;
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private PackageRepo packageRepo;
    @Autowired
    private BookingRepo bookingRepo;

    public String admin(HttpSession session) {

        String role=(String)session.getAttribute("Role");

        if(role==null || !role.equals("admin"))
        {
            throw new RuntimeException("Access denied");
        }
        return "Welcome Admin!";
    }

    public String user(HttpSession session) {
        String role=(String)session.getAttribute("Role");
        if(role==null&&!role.equals("CUSTOMER"))
        {
            throw new RuntimeException("Access denied");
        }
        return "Welcome User!";
    }

    public String driver(HttpSession session) {
        String role=(String)session.getAttribute("Role");
        if(role==null&&!role.equals("driver"))
        {
            throw new RuntimeException("Access denied");
        }
        return "Welcome Driver!";
    }

    public DashboardDTO adminDashboard(HttpSession session) {
        Long UserId=(Long)session.getAttribute("userID");
        Registration admin=registrationRepo.findById(UserId).get();
        DashboardDTO dashboardDTO=new DashboardDTO();
        dashboardDTO.setFirstName(admin.getFirstName());
        dashboardDTO.setEmail(admin.getEmail());
        dashboardDTO.setRole(admin.getRole());

        dashboardDTO.setTotalUsers(registrationRepo.count());
        dashboardDTO.setTotalDrivers(driverRepo.count());
        dashboardDTO.setTotalPayments(paymentRepo.count());
        dashboardDTO.setTotalPackages(packageRepo.count());
        dashboardDTO.setTotalBookings(bookingRepo.count());
        return dashboardDTO;
    }

    public DriverDashboardDTO driverDashboard(HttpSession session) {
        Long UserId=(Long)session.getAttribute("userId");
        Driver driver=driverRepo.findById(UserId).get();
        Registration user=registrationRepo.findById(UserId).get();
        DriverDashboardDTO dashboardDTO=new DriverDashboardDTO();
        dashboardDTO.setFirstName(user.getFirstName());
        dashboardDTO.setEmail(user.getEmail());
        dashboardDTO.setPhone(user.getPhoneNumber());
        dashboardDTO.setVehicleName(driver.getVehicleName());
        dashboardDTO.setVehicleType(driver.getVehicleType());
        dashboardDTO.setExperienceYears(driver.getExperienceYears());
        dashboardDTO.setStatus(driver.getStatus());
        dashboardDTO.setTotalBookings(bookingRepo.countBookingByDriver(UserId));
        dashboardDTO.setTotalPackages(bookingRepo.countPackagesByDriver(UserId));
       dashboardDTO.setAverageRating(bookingRepo.averageRatingByDriver(UserId));
       dashboardDTO.setPendingPackages(bookingRepo.countPendingPacakgesByDriver(UserId));
        dashboardDTO.setApprovedPackages(bookingRepo.countApprovedPackagesByDriver(UserId));
        dashboardDTO.setCompletedTrips(bookingRepo.countCompletedTripsByDriver(UserId));
        return dashboardDTO;
    }

    public CustomerDashboardDTO customerDashboard(HttpSession session) {
        Long userId=(Long)session.getAttribute("userId");
        Registration customer=registrationRepo.findById(userId).get();
        CustomerDashboardDTO dashboardDTO=new CustomerDashboardDTO();
        dashboardDTO.setName(customer.getFirstName());
        dashboardDTO.setPhone(customer.getPhoneNumber());
        dashboardDTO.setEmail(customer.getEmail());
        dashboardDTO.setTotalBooking(bookingRepo.totalBookingByCustomer(userId));
        dashboardDTO.setCompletedTrips(bookingRepo.completedTripsByCustomer(userId));
        dashboardDTO.setCancelledTrips(bookingRepo.cancelledTripsByCustomer(userId));
        return dashboardDTO;

    }
}
