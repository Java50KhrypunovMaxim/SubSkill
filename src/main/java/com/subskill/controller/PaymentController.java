package com.subskill.controller;

import com.subskill.dto.MicroSkillDto;
import com.subskill.models.MicroSkill;
import com.subskill.service.OrderedMicroSkillService;
import com.subskill.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("api/v1/payment")
@RequiredArgsConstructor
@Slf4j
@Validated
@CrossOrigin(maxAge = 3600, origins = "*")
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderedMicroSkillService orderedMicroSkillService;

    @GetMapping("/total")
    public BigDecimal totalPaymentByUserId() {
        return paymentService.getTotalPaymentByUserId();
    }

    @PostMapping("/ordered")
    public void fromCartToOrderedMicroSkill() {
        orderedMicroSkillService.moveFromCartToOrderedMicroSKill();
    }

    @GetMapping("/all")
    public Set<MicroSkill> allOrderedMicroskill() {
        return orderedMicroSkillService.allOrderedMicroskill();
    }

    @GetMapping("/{microskillId}")
    public MicroSkillDto singleBuy(@PathVariable long microskillId) {
        return orderedMicroSkillService.singleMicroSkillBuy(microskillId);
    }
}
