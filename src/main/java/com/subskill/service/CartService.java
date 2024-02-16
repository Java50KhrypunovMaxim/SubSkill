package com.subskill.service;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;

public interface CartService {
    CartDto addMicroSkillToCart(MicroSkillDto microSkillDto);
    void deleteMicroSkillFromCart(long id);
}
