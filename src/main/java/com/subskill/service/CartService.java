package com.subskill.service;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.models.Cart;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CartService {

    CartDto addMicroSkillToCart(Long microskillId, Long userId);

    void deleteMicroSkillFromCart(Long microskillId);

    Set<MicroSkillDto> allMicroSkillsInCart(Long cartId);
}
