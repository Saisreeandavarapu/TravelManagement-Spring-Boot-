//package com.travel.TravelManagement.AdvancedServices;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//@Service
//public class WhatsAppService {
//    @Value("${twilio.account.sid}")
//    private String accountSid;
//    @Value("${twilio.auth.token}")
//    private String authToken;
//    @Value("${twilio.whatsapp.number}")
//    private String fromNumber;
//
//    public void sendMessage(String phoneNumber,String message)
//    {
//        Twilio.init(accountSid, authToken);
//
//        Message.creator(
//                new com.twilio.type.PhoneNumber(
//                        "whatsapp:+91" + phoneNumber),
//                new com.twilio.type.PhoneNumber(
//                        fromNumber),
//                message
//        ).create();
//
//
//    }
//
//}
