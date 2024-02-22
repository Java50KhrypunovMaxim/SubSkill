package com.subskill.service.impl;

import com.subskill.service.SendMail;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SendMailServiceImpl implements SendMail {
    private final JavaMailSender javaMailSender;

    @Override
    public String send(String mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Welcome from team SubSkill");
        simpleMailMessage.setText("Hello from team SubSkill, Happy to see you on our website!");
        simpleMailMessage.setTo(mail);
        javaMailSender.send(simpleMailMessage);
        return "Mail sent successfully";
    }
}
