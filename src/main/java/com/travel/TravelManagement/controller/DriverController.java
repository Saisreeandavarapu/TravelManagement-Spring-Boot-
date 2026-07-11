package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.model.Driver;
import com.travel.TravelManagement.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Driver")
@CrossOrigin("*")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @PostMapping("/details")
    public List<Driver> driverDetails(@RequestBody Driver driver){
        return driverService.driverDetails(driver);
    }
    @GetMapping("/allDetails")
    public List<Driver> getAllDrivers(){
        return driverService.getAllDrivers();
    }
    @GetMapping("/details/{id}")
    public Driver getDriverById(@PathVariable Long id){
        return driverService.getDriverById(id);
    }
    @PutMapping("/updateDetails/{id}")
    public List<Driver> updateDriver(@PathVariable Long id, @RequestBody Driver driver){
        return driverService.updateDriver(id,driver);
    }
    @DeleteMapping("/deleteDetails/{id}")
    public List<Driver> deleteDriverById(@PathVariable Long id){
        return driverService.deleteDriverById(id);
    }
    @PutMapping("/status/{id}")
    public List<Driver> updateDriverStatus(@PathVariable Long id, @RequestBody Driver driver){
        return driverService.updateDriverStatus(id,driver);
    }
    @GetMapping("/count")
    public Long getDriverCount(){
        return driverService.getDriverCount();
    }
}
