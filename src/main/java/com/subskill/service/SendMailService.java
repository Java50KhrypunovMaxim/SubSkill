package com.subskill.service;

import com.subskill.models.User;
import org.springframework.mail.SimpleMailMessage;

import java.util.Locale;

public interface SendMailService {
    String send(String mail);
    String sendToken(String mail,String token);
//    void constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user);
    SimpleMailMessage constructEmail(String subject, String body, User user);

}
