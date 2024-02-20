package com.subskill.controller;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class CartController {
    CartService cartService;


        @Operation(summary = "Add new MicroSkills to cart")
    @PostMapping("/add")
    CartDto addMicroSkillToCart(@RequestBody @Valid MicroSkillDto microSkillDto) {
        log.debug("Add microSkills: received cart data: {}", microSkillDto);
        return cartService.addMicroSkillToCart(microSkillDto);
    }
    @Operation(summary = "Remove MicroSkills from cart")
    @PutMapping("/delete/{id}")
    void deleteMicroSkillFromCart(@PathVariable  long id) {
        log.debug("remove microSkill: remove cart microskill {} from data", id);
         cartService.deleteMicroSkillFromCart(id);
    }
    @Operation(summary = "List of MicroSkills in Cart")
    @GetMapping("/all")
    List<CartDto> listOMicroSkillInCart() {
        log.debug("List of users have been received");
        return cartService.allMicroSkillsInCart();
    }
}
