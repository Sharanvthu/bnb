package com.airbnb.bnb.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.io.File;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javamailSender;


    public void sendEmailWithAttachment(String to, String subject, String text, File filePath) throws MessagingException {
        // Create a MimeMessage

        MimeMessage message = javamailSender.createMimeMessage();

        // Use MimeMessageHelper to configure the message
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true); // 'true' indicates multipart

        // Set email attributes
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text);

        // Attach the file

        if (filePath != null && filePath.exists()) {
            mimeMessageHelper.addAttachment(filePath.getName(),filePath);

            // Send the email
            javamailSender.send(message);
            System.out.println("Email sent successfully with attachment.");
        }
    }
}
