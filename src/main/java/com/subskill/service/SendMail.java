package com.subskill.service;

import com.subskill.models.Mail;
import org.springframework.stereotype.Service;

@Service
public interface SendMail {
    String send(Mail mail);
}
