package com.travel.TravelManagement.AdvancedServices;

import com.travel.TravelManagement.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(
            String email,
            String subject,
            String body){

        try{

            System.out.println(
                    "Before Sending Mail");

            SimpleMailMessage message =
                    new SimpleMailMessage();

            message.setTo(email);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

            System.out.println(
                    "Mail Sent Successfully");

        }
        catch(Exception e){

            System.out.println(
                    "Mail Sending Failed");

            e.printStackTrace();

        }

    }

    public void sendTravelReminder(Booking booking, String subject, String message) {
        SimpleMailMessage message1=new SimpleMailMessage();
        message1.setTo(booking.getRegistration().getEmail());
        message1.setSubject(subject);
        message1.setText(message);
        mailSender.send(message1);
    }
//    public void registerUserEamil(String to,String subject,String text)
//    {
//        SimpleMailMessage message=new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }
//    public void paymentSentEmail(String to,String subject,String text)
//    {
//        SimpleMailMessage message=new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }
//    public void afterPaymentEmail(String to,String subject,String text)
//    {
//        SimpleMailMessage message=new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        mailSender.send(message);
//    }

}
