package com.subskill.service.impl;

import com.subskill.models.Mail;
import com.subskill.service.SendMail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailServiceImpl implements SendMail {
    private final JavaMailSender javaMailSender;

    public SendMailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
        @Override
    public String send(Mail mail) {
            Mail welcome = new Mail("Welcome from team SubSkill",mail.userDto(),"Hello from team SubSkill, Happy to see you on our website!");
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setSubject(welcome.title());
            simpleMailMessage.setText(welcome.message());
            simpleMailMessage.setTo(welcome.userDto().email());
            javaMailSender.send(simpleMailMessage);
            return "Mail sent successfully";

    }


}
