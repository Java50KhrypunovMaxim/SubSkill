package com.subskill.service.impl;

import com.subskill.models.User;
import com.subskill.service.SendMailService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
public class SendMailServiceImpl implements SendMailService {
    private final JavaMailSender javaMailSender;
    private final MessageSource messageSource;
     private final Environment env;


    @Override
    public String send(String mail) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Welcome from team SubSkill");
        simpleMailMessage.setText("Hello from team SubSkill, Happy to see you on our website!");
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setFrom("kasmatuy2k17@gmail.com");
        javaMailSender.send(simpleMailMessage);
        return "Mail sent successfully";
    }
    @Override
    public String sendToken(String mail,String token) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("Welcome from team SubSkill");
        simpleMailMessage.setFrom("example@example.com");
        simpleMailMessage.setText("Hello from team SubSkill,this is your token : " + token);
        simpleMailMessage.setTo(mail);
        javaMailSender.send(simpleMailMessage);
        return "Mail sent successfully";
    }

    public void   constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/changePassword?token=" + token;
        final String message = messageSource.getMessage("Reset Password", null, locale);
        constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    public SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(env.getProperty("support.email"));
        return email;
    }

}
