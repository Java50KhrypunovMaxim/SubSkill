package com.subskill.controller;

import com.subskill.service.SendMailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600, origins = "*")
@RestController
@RequestMapping("api/v1/mail/")
@Slf4j
@AllArgsConstructor
public class SendMailController {
    private final SendMailService sendMailService;

    @PostMapping("/send")
    public String sendMail(@RequestParam String mail) {
        return sendMailService.send(mail);
    }
}