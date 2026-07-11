package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.DTO.LoginDTO;
import com.travel.TravelManagement.model.Registration;
import com.travel.TravelManagement.services.RegistrationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin("*")
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private RegistrationService service;
    @PostMapping("/user")
    public List<Registration> createUser(@RequestBody Registration registration)
    {
        return service.createUser(registration);
    }
    @GetMapping("/allUsers")
    public List<Registration> getAllUsers()
    {
        return service.getAllUsers();
    }
    @PutMapping("/update")
    public List<Registration> updateUser(@RequestBody Registration registration ,@PathVariable Long id)
    {
        return service.updateUser(registration,id);
    }
    @GetMapping("/user/{id}")
    public Registration getUserById(@PathVariable Long id)
    {
        return service.getUserById(id);
    }
    @DeleteMapping("/delete/{id}")
    public List<Registration> deleteUserById(@PathVariable Long id)
    {
        return service.deleteUserById(id);
    }
    @PutMapping("/resetPassword/{id}")
    public Registration resetPassword(@PathVariable Long id, @RequestBody Registration registration)
    {
        return service.resetPassword(id,registration);
    }
    @PostMapping("/login")
    public Registration login(@RequestBody LoginDTO login,HttpSession session)
    {
        return service.login(login,session);
    }
    @GetMapping("/count")
    public Long usersCount()
    {
        return service.usersCount();
    }

    @PostMapping("/logout")
    public String  Logout(HttpSession session)
    {
        return service.Logout(session);
    }
    @GetMapping("/profile")
    public Registration getUserProfile(HttpSession session)
    {
        return service.getUserProfile(session);
    }

}
