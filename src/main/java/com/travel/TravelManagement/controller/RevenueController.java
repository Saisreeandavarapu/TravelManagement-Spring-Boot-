package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.DTO.RevenueDTO;
import com.travel.TravelManagement.services.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/revenue")
public class RevenueController {
    @Autowired
    private RevenueService revenueService;
    @GetMapping
    public RevenueDTO revenueDetails()
    {
        return revenueService.revenueDetails();
    }
}
