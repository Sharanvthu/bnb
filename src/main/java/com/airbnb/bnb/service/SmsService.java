package com.airbnb.bnb.service;


import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SmsService {

    private static final Logger logger=  LoggerFactory.getLogger(SmsService.class);


    @Value("${twilio.phone-number}")
    private String twilioPhoneNumber;

    public String sendSms(String to, String body) {
        try {

            logger.info("Starting to seand SMS  - "+new Date());

            Message message = Message.creator(
                    new PhoneNumber(to),    // To phone number
                    new PhoneNumber(twilioPhoneNumber),  // From Twilio phone number
                    body                    // Message body
            ).create();


            logger.info("Send SMS  - "+new Date());


            return message.getSid(); // Return the message SID for confirmation
        } catch (Exception e) {


            logger.error(e.getMessage());
            return "error";
        }
    }
}
