package com.subskill.controller;

import com.subskill.models.OrderedMicroskill;
import com.subskill.service.PaymentService;
import com.subskill.service.OrderedMicroSkillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
@Slf4j
@Validated
@CrossOrigin(maxAge = 3600, origins = "*")
public class PaymentController {

    private final PaymentService paymentService;

    private final OrderedMicroSkillService orderedMicroSkillService;


    @GetMapping("/{userId}")
    public BigDecimal processPayment(@PathVariable Long userId) {
        return paymentService.getTotalPaymentByUserId(userId);
    }
    @PostMapping ("/ordered/{userId}")
    public void fromCartToOrderedMicroSkill(@PathVariable Long userId) {

          orderedMicroSkillService.moveFromCartToOrderedMicroSKill(userId);
    }
    @GetMapping("/all")
    public List<OrderedMicroskill> allOrderedMicroskill() {
        return orderedMicroSkillService.allOrderedMicroskill();
    }

}
