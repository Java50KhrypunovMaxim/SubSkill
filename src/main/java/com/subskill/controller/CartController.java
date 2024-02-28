package com.subskill.controller;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class CartController {
    private final CartService cartService;

    @Operation(summary = "Add new MicroSkills to cart")
    @PostMapping("/add/{microSkillId}")
    CartDto addMicroSkillToCart(@PathVariable long microSkillId) {
        log.debug("Add MicroSkill to cart: received MicroSkill ID: {}", microSkillId);
        return cartService.addMicroSkillToCart(microSkillId);
    }
    @Operation(summary = "Remove MicroSkills from cart")
    @DeleteMapping("/delete/{microSkillId}")
    void deleteMicroSkillFromCart(@PathVariable  long cartId) {
        log.debug("remove microSkill: remove cart microskill {} from data", cartId);
         cartService.deleteMicroSkillFromCart(cartId);
    }
    @Operation(summary = "List of MicroSkills in Cart")
    @GetMapping("/all")
    List<CartDto> listOMicroSkillInCart() {
        log.debug("List of users have been received");
        return cartService.allMicroSkillsInCart();
    }
}
