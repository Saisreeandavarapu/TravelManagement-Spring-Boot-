package com.travel.TravelManagement.controller;

import com.travel.TravelManagement.AdvancedServices.EmailOtpService;
import com.travel.TravelManagement.DTO.VerifyOtpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.PrimitiveIterator;

@RestController
@CrossOrigin("*")
@RequestMapping("/otp")
public class EmailOtpController {
    @Autowired
    private EmailOtpService emailOtpService;

    @PostMapping("/send/{email}")
    public String sendOTPemail(@PathVariable String email)
    {
      return   emailOtpService.sendOTPemail(email);
    }

    @PostMapping("/verify")
    public String verifyOTP( @RequestBody VerifyOtpDTO verifyOtpDTO)
    {
        return emailOtpService.verifyOTP(verifyOtpDTO);
    }
    @PostMapping("/resend/{email}")
    public String resendOTPemail(@PathVariable String email)
    {
        return emailOtpService.resendOTPemail(email);
    }
    @PostMapping("/forgotpassword/{email}")
    public String forgotpasswordEmail(@PathVariable String email)
    {
        return emailOtpService.forgotpasswordEmail(email);
    }
    @PostMapping("/verifyForgotOtp")
    public String verifyForgotOtp(@RequestBody VerifyOtpDTO verifyOtpDTO)
    {
        return emailOtpService.verifyForgotOtp(verifyOtpDTO);
    }


}
