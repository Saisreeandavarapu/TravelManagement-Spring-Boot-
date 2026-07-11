package com.travel.TravelManagement.services;

import com.travel.TravelManagement.model.Driver;
import com.travel.TravelManagement.repo.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    @Autowired
    private DriverRepo repo;

    public List<Driver> driverDetails(Driver driver) {
         repo.save(driver);
         return repo.findAll();
    }

    public List<Driver> getAllDrivers() {
        return repo.findAll();
    }

    public Driver getDriverById(Long id) {
        return repo.findById(id).orElseThrow(()-> new RuntimeException("not found data"));
    }

    public List<Driver> updateDriver(Long id, Driver driver) {
        Driver oldDriver=repo.findById(id).orElseThrow(()-> new RuntimeException("not found data"));
        oldDriver.setVehicleName(driver.getVehicleName());
        oldDriver.setVehicleType(driver.getVehicleType());
        oldDriver.setLicenseNumber(String.valueOf(driver.getLicenseNumber()));
        oldDriver.setExperienceYears(driver.getExperienceYears());
        oldDriver.setAddress(driver.getAddress());
        oldDriver.setCity(driver.getCity());
        oldDriver.setState(driver.getState());
         repo.save(oldDriver);
         return repo.findAll();
    }

    public List<Driver> deleteDriverById(Long id) {
        repo.deleteById(id);
        return repo.findAll();
    }

    public List<Driver> updateDriverStatus(Long id, Driver driver) {
        Driver oldDriver=repo.findById(id).orElseThrow(()-> new RuntimeException("not found data"));
        oldDriver.setStatus(driver.getStatus());
        repo.save(oldDriver);
        return repo.findAll();
    }

    public Long getDriverCount() {
       return repo.count();
    }
}
