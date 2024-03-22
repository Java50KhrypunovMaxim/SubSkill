package com.subskill.controller;

import com.subskill.models.PaymentInfo;
import com.subskill.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
@Slf4j
@Validated
@CrossOrigin(maxAge = 3600, origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{cartId}")
    public ResponseEntity<PaymentInfo> processPayment(@PathVariable Long cartId) {
        ResponseEntity<PaymentInfo> response = paymentService.addMicroSkillPriceInCart(cartId);
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
