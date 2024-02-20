package com.subskill.controller;

import com.subskill.models.Mail;
import com.subskill.service.SendMail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(maxAge = 3600, origins = "*")
@RestController
@RequestMapping("api/v1/mail/")
@Slf4j

public class SendMailController {



    private final SendMail mailService;

    public SendMailController(@Qualifier("sendMail") SendMail mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    public String sendMail(@RequestBody Mail mail) {
        return mailService.send(mail);
    }
}