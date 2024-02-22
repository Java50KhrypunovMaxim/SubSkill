package com.subskill.service;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    CartDto addMicroSkillToCart( long microskill_id);
    void deleteMicroSkillFromCart(long cart_id);
    List<CartDto> allMicroSkillsInCart();
}
