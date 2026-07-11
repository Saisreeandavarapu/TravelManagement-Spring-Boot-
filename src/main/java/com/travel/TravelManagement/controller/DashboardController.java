package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.DTO.CustomerDashboardDTO;
import com.travel.TravelManagement.DTO.DashboardDTO;
import com.travel.TravelManagement.DTO.DriverDashboardDTO;
import com.travel.TravelManagement.services.DashboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/Dashboard")
public class DashboardController {
    @Autowired
    private DashboardService service;

    @GetMapping("/admin")
    public String admin(HttpSession session) {
        return service.admin(session);
    }
    @GetMapping("/user")
    public String user(HttpSession session) {
        return service.user(session);
    }
    @GetMapping("/driver")
    public String driver(HttpSession session) {
        return service.driver(session);
    }
    @GetMapping("/admin/dashboard")//one api full admin dashboard
    public DashboardDTO adminDashboard(HttpSession session)
    {
        return service.adminDashboard(session);
    }
    @GetMapping("/driver/dashboard")
    public DriverDashboardDTO driverDashboard(HttpSession session)
    {
        return service.driverDashboard(session);
    }
    @GetMapping("/user/dashboard")
    public CustomerDashboardDTO customerDashboard(HttpSession session)
    {
        return service.customerDashboard(session);
    }
}
