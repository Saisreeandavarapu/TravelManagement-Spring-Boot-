package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.model.Packages;
import com.travel.TravelManagement.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Packages")
@CrossOrigin("*")
public class PackageController {
    @Autowired
    private PackageService service;

    @PostMapping("/add")
    public List<Packages> save(@RequestBody Packages p){
        return service.save(p);
    }
    @GetMapping("allPackages")
    public List<Packages> getAllPackages(){
        return service.getAllPackages();
    }
    @GetMapping("/{id}")
    public Packages getOne(@PathVariable Long id){
        return service.getOne(id);
    }
    @PutMapping("/update/{id}")
    public List<Packages> updateById(@PathVariable Long id, @RequestBody Packages p){
        return service.updateById(id,p);
    }
    @PutMapping("/status/{id}")
    public List<Packages> updateStatus(@PathVariable Long id, @RequestBody Packages p){
        return service.updateStatus(id,p);
    }
    @DeleteMapping("/delete/{id}")
    public List<Packages> deleteById(@PathVariable Long id){
        return service.deleteById(id);
    }
    @GetMapping("/count")
    public Long packageCount(){
        return service.packageCount();
    }
}
