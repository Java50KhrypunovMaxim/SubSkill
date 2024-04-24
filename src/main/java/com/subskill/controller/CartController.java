package com.subskill.controller;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "Add new MicroSkills to cart")
    @PostMapping("/add/{microSkillId}")
    CartDto addMicroSkillToCart(@PathVariable Long microSkillId) {
        log.debug("Add MicroSkill to cart: received MicroSkill ID: {}, ", microSkillId);
        return cartService.addMicroSkillToCart(microSkillId);
    }

    @Operation(summary = "Remove MicroSkills from cart")
    @DeleteMapping("/delete/all")
    void deleteMicroSkillFromCart() {
        log.debug(" remove all microskill from cart");
        cartService.deleteAllMicroSKillFromCart();
    }
    @Operation(summary = "Remove MicroSkills from cart")
    @DeleteMapping("/delete/{microskillId}")
    void deleteMicroSkillFromCart(@PathVariable Long microskillId) {
        log.debug(" remove  microskill with id : {} from cart", microskillId);
        cartService.deleteMicroSkillFromCart(microskillId);
    }
    @Operation(summary = "Total money in Cart")
    @GetMapping("/total")
    public BigDecimal totalPaymentByUserId() {
        return cartService.totalMoneyForMicroSkill();
    }
    @Operation(summary = "List of MicroSkills in Cart")
    @GetMapping("/all")
    Set<MicroSkillDto> listOMicroSkillInCart() {
        log.warn("List of users have been received ");
        return cartService.allMicroSkillsInCart();
    }

}
