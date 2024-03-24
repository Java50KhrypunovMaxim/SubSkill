package com.subskill.service;

import com.subskill.dto.CartDto;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartDto addMicroSkillToCart(Long microskillId, Long userId);

    void deleteMicroSkillFromCart(Long microskillId);

    CartDto allMicroSkillsInCart(Long userId);
}
