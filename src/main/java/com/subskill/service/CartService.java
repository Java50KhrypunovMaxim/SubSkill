package com.subskill.service;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;

import java.math.BigDecimal;
import java.util.Set;


public interface CartService {

    CartDto addMicroSkillToCart(Long microskillId);

    void deleteMicroSkillFromCart(Long microskillId);
    void deleteAllMicroSKillFromCart();
    BigDecimal totalMoneyForMicroSkill();
    Set<MicroSkillDto> allMicroSkillsInCart();
}
