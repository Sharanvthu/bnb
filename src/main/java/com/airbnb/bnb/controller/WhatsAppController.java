//package com.airbnb.bnb.controller;
//
//import com.airbnb.bnb.service.WhatsAppService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/whatsapp")
//public class WhatsAppController {
//
//    @Autowired
//    private WhatsAppService whatsAppService;
//
//    @PostMapping("/send")
//    public String sendMessage(@RequestParam String to,
//                              @RequestParam String text) {
//        whatsAppService.sendWhatsAppMessage(to, text);
//        return "Message sent successfully!";
//    }
//}
