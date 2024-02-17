package com.subskill.service;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    CartDto addMicroSkillToCart(MicroSkillDto microSkillDto);
    void deleteMicroSkillFromCart(long id);
    List<CartDto> allMicroSkillsInCart();
}
