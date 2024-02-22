package com.subskill.controller;

import com.subskill.service.SendMail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600, origins = "*")
@RestController
@RequestMapping("api/v1/mail/")
@Slf4j
@AllArgsConstructor
public class SendMailController {
    private final SendMail mailService;

    @PostMapping("/send")
    public String sendMail(@RequestParam String mail) {
        return mailService.send(mail);
    }
}