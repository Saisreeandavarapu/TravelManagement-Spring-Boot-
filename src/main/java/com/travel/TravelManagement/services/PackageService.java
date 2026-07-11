package com.travel.TravelManagement.services;

import com.travel.TravelManagement.model.Driver;
import com.travel.TravelManagement.model.Packages;
import com.travel.TravelManagement.repo.DriverRepo;
import com.travel.TravelManagement.repo.PackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {
    @Autowired
    private PackageRepo repo;


    public List<Packages> save(Packages p) {
         repo.save(p);
         return repo.findAll();
    }

    public List<Packages> getAllPackages() {
        return repo.findAll();
    }

    public Packages getOne(Long id) {
        return repo.findById(id).orElseThrow(()->new RuntimeException("not found data"));
    }

    public List<Packages> updateById(Long id, Packages p) {
        Packages packages= repo.findById(id).orElseThrow(()-> new RuntimeException("not found data"));
        packages.setTitle(p.getTitle());
        packages.setDescription(packages.getDescription());
        packages.setDriver(packages.getDriver());
        packages.setDurationDays(packages.getDurationDays());
        packages.setDiscountPercentage(packages.getDiscountPercentage());
        packages.setOriginalPrice(packages.getOriginalPrice());
        packages.setOfferPrice(packages.getOfferPrice());
        packages.setMaxPeople(packages.getMaxPeople());
         repo.save(packages);
         return repo.findAll();
    }

    public List<Packages> updateStatus(Long id, Packages p) {
        Packages packageStatus= repo.findById(id).orElseThrow(()->new RuntimeException("not found data"));
        packageStatus.setStatus(p.getStatus());
         repo.save(packageStatus);
         return repo.findAll();
    }

    public List<Packages> deleteById(Long id) {
         repo.deleteById(id);
         return repo.findAll();
    }

    public Long packageCount() {
        return repo.count();
    }
}
