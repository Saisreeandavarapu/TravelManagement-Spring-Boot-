package com.travel.TravelManagement.services;

import com.travel.TravelManagement.AdvancedServices.EmailService;
import com.travel.TravelManagement.DTO.LoginDTO;
import com.travel.TravelManagement.model.Registration;
import com.travel.TravelManagement.repo.RegistrationRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.servlet.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {
    @Autowired
    private RegistrationRepo repo;
    @Autowired
    private EmailService emailService;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;
    public List<Registration> createUser(Registration registration) {
        registration.setPassword(passwordEncoder.encode(registration.getPassword()));
         repo.save(registration);
        String subject = "Registration Successful";

        String body = """
Hello %s,

Your registration has been completed successfully.

Registration Details:

Name : %s %s
Email : %s
Phone Number : %s

Thank you for registering with Travel Management System.

Travel Management Team
""".formatted(
        registration.getFirstName(),
                registration.getFirstName(),
                registration.getLastName(),
                registration.getEmail(),
                registration.getPhoneNumber()
        );
        emailService.sendEmail(registration.getEmail(),subject,body);
        System.out.println("Registration Successful sent email msg");
         return repo.findAll();
    }

    public List<Registration> getAllUsers() {
        return repo.findAll();
    }

    public List<Registration> updateUser(Registration registration,Long id) {
        Registration updateRegistration = repo.findById(id).orElseThrow(()->new RuntimeException("Registration not found"));
        updateRegistration.setFirstName(registration.getFirstName());
        updateRegistration.setLastName(registration.getLastName());
        updateRegistration.setEmail(registration.getEmail());
        updateRegistration.setPassword(registration.getPassword());
        updateRegistration.setPhoneNumber(registration.getPhoneNumber());
        updateRegistration.setRole(registration.getRole());
        repo.save(updateRegistration);
        return repo.findAll();

    }

    public Registration getUserById(Long id) {
        return repo.findById(id).orElseThrow(()->new RuntimeException("Registration not found"));
    }

    public List<Registration> deleteUserById(Long id) {
         repo.deleteById(id);
        return repo.findAll();

    }

    public Registration resetPassword(Long id, Registration registration) {
        Registration updateRegistration = repo.findById(id).orElseThrow(()->new RuntimeException("Registration not found"));
        updateRegistration.setPassword(registration.getPassword());
        return repo.save(updateRegistration);
    }

    public Registration login(LoginDTO login,HttpSession session) {
        Registration registration = repo.findByEmail(login.getEmail());
        boolean valid=passwordEncoder.matches(login.getPassword(),registration.getPassword());
        if (!valid) {
           throw new RuntimeException("Registration not found");

        }
        session.setAttribute("userID",registration.getId());
        session.setAttribute("Role",registration.getRole());//role based open dashboard.
        return registration;

    }

    public Long usersCount() {
        return repo.count();
    }

    public String Logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully";
    }

    public Registration getUserProfile(HttpSession session) {
        Long UserID=(Long) session.getAttribute("userID");

        if(UserID==null)
        {
            throw  new RuntimeException("Not registered user");
        }
        return repo.findById(UserID).orElseThrow(()->new RuntimeException("Not registered user"));
    }
}
