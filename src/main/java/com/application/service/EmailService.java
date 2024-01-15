package com.application.service;

import com.application.model.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    public void sendSuggestionEmail(UserProfile user, String messageText) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("kamilpraseej742@gmail.com");
        message.setTo(user.getEmailId());
        message.setSubject("Help Desk Message");
        message.setText("\n\nDear Admin,\n\n"+messageText);
        javaMailSender.send(message);

    }
}
