package com.travel.TravelManagement.AdvancedServices;

import com.travel.TravelManagement.DTO.VerifyOtpDTO;
import com.travel.TravelManagement.model.EmailOtp;
import com.travel.TravelManagement.model.Registration;
import com.travel.TravelManagement.repo.EmailOtpRepo;
import com.travel.TravelManagement.repo.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class EmailOtpService {

    @Autowired
    private EmailOtpRepo  emailOtpRepo;
    @Autowired
    private RegistrationRepo registrationRepo;
    @Autowired
    private EmailService emailService;

    public String generateOTP()
    {
        Random random = new Random();
        return String.valueOf(100000  +random.nextInt(900000));
    }
    public String  sendOTPemail(String email) {
        Registration user=registrationRepo.findByEmail(email);
        String otp=generateOTP();
        Optional<EmailOtp> optional = Optional.ofNullable(emailOtpRepo.findByEmail(email));

        EmailOtp emailOtp;

        if (optional.isPresent()) {

            emailOtp = optional.get();

        } else {

            emailOtp = new EmailOtp();

        }
        emailOtp.setEmail(email);
        emailOtp.setOtp(otp);
        emailOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        emailOtp.setAttempts(0);
        emailOtp.setVerified(false);
        emailOtpRepo.save(emailOtp);

        String body = """
Hello %s,

Your One-Time Password (OTP) for email verification is:

%s

This OTP is valid for 5 minutes.

If you did not request this verification, please ignore this email.

Thank you,
Travel Management Team
"""
                .formatted(user.getFirstName(),otp);
                        emailService.sendEmail(email, "Travel Management - Email Verification OTP",body);
                return "OTP sent Successfully";
    }

    public String  verifyOTP(VerifyOtpDTO verifyOtpDTO) {
        EmailOtp emailOtp=emailOtpRepo.findByEmail(verifyOtpDTO.getEmail());

        if(!emailOtp.getOtp().equals(verifyOtpDTO.getOtp()))
        {
            return "Invalid OTP";
        }
        if(emailOtp.getExpiryTime().isBefore(LocalDateTime.now()))
        {
            return "Expired OTP";
        }
        if(emailOtp.getAttempts()>=3)
        {
            return "Maximum number of attempts reached";
        }
        if(!emailOtp.getOtp().equals(verifyOtpDTO.getOtp()))
        {
            emailOtp.setAttempts(emailOtp.getAttempts()+1);
            return "Invalid OTP. Remaining attempts: " +(3-emailOtp.getAttempts());

        }
        emailOtp.setVerified(true);
        emailOtpRepo.save(emailOtp);
        return "Email Verified";
    }

    public String resendOTPemail(String email) {
        Registration user = registrationRepo.findByEmail(email);

        String otp=generateOTP();
        Optional<EmailOtp> optional = Optional.ofNullable(emailOtpRepo.findByEmail(email));

        EmailOtp emailOtp;

        if (optional.isPresent()) {

            emailOtp = optional.get();

        } else {

            emailOtp = new EmailOtp();

        }

        emailOtp.setEmail(email);
        emailOtp.setOtp(otp);
        emailOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        emailOtpRepo.save(emailOtp);
        String body = """
Hello %s,

Your new OTP for email verification is:

%s

This OTP is valid for 5 minutes.

If you did not request this OTP, please ignore this email.

Thank you,
Travel Management Team
"""
                .formatted(user.getFirstName(),otp);
        emailService.sendEmail(email, "Travel Management - Email Verification OTP",body);
        return "new OTP sent Successfully";

    }

    public String forgotpasswordEmail(String email) {

        Registration user=registrationRepo.findByEmail(email);

        String otp=generateOTP();

        Optional<EmailOtp> optional = Optional.ofNullable(emailOtpRepo.findByEmail(email));

        EmailOtp emailOtp;

        if (optional.isPresent()) {

            emailOtp = optional.get();

        } else {

            emailOtp = new EmailOtp();

        }
        emailOtp.setEmail(email);
        emailOtp.setOtp(otp);
        emailOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        emailOtpRepo.save(emailOtp);
        String body = """
Hello %s,

We received a request to reset your password.

Your OTP is:

%s

This OTP is valid for 5 minutes.

If you did not request a password reset, please ignore this email.

Travel Management Team
"""
                .formatted(user.getFirstName(),otp);
        emailService.sendEmail(email, "Travel Management - Email Reset",body);
        return "OTP sent Successfully";
    }

    public String verifyForgotOtp(VerifyOtpDTO verifyOtpDTO) {
        EmailOtp emailOtp=emailOtpRepo.findByEmail(verifyOtpDTO.getEmail());

        if(!emailOtp.getOtp().equals(verifyOtpDTO.getOtp()))
        {
            return "Invalid OTP";
        }
        if(emailOtp.getExpiryTime().isBefore(LocalDateTime.now()))
        {
            return "Expired OTP";
        }
        emailOtpRepo.save(emailOtp);
        return "Email Verified";
    }
}
