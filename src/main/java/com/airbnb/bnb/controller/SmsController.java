package com.airbnb.bnb.controller;

import com.airbnb.bnb.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @PostMapping("/send")
    public String sendSms(@RequestParam String to, @RequestParam String message) {
        return smsService.sendSms(to, message);
    }
}
