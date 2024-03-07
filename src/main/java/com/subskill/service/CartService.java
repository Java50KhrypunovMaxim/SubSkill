package com.subskill.service;

import com.subskill.dto.CartDto;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartDto addMicroSkillToCart(Long microskillId);

    void deleteMicroSkillFromCart(Long cartId);

    CartDto allMicroSkillsInCart(Long userId);
}
