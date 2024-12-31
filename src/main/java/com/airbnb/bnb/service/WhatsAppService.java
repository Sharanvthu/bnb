//package com.airbnb.bnb.service;
//
//
//import org.springframework.stereotype.Service;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.net.URI;
//import java.util.List;
//
//    @Service
//    public class WhatsAppService {
//
//
//        @Value("${twilio.whatsapp.from}")
//        private String fromWhatsAppNumber;
//
//        public void sendWhatsAppMessage(String toWhatsAppNumber, String messageText) {
//            //Twilio.init(accountSid, authToken);
//
//            Message message = Message.creator(
//                            new PhoneNumber("whatsapp:" + toWhatsAppNumber), // To number
//                            new PhoneNumber(fromWhatsAppNumber), // From number
//                            messageText // Text message
//                    ).create();
//
//            System.out.println("Message SID: " + message.getSid());
//        }
//    }
//
//
//
//
//
//
//
