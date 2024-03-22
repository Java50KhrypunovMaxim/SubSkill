package com.subskill.service;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CartService {
    CartDto addMicroSkillToCart(Long microskillId, Long userId);

    void deleteMicroSkillFromCart(Long cartId);

    Set<MicroSkillDto> allMicroSkillsInCart(Long cartId);
}
