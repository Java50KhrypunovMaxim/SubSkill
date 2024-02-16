package com.subskill.service.impl;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.repository.CartRepository;
import com.subskill.service.CartService;
import lombok.AllArgsConstructor;

import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public CartDto addMicroSkillToCart(MicroSkillDto microSkillDto) {
        return cartRepository.save(microSkillDto);
    }

    @Override
    public void deleteMicroSkillFromCart(long id) {
        Cart cart = cartRepository.findMicroSkillByUser(id);
        if (cart != null) {
            List<MicroSkill> listOfMicroSkills = cart.getListOfMicroSkills();
            Iterator<MicroSkill> iterator = listOfMicroSkills.iterator();
            while (iterator.hasNext()) {
                MicroSkill microSkill = iterator.next();
                if (microSkill.getId() == id) {
                    iterator.remove();
                    break;
                }
            }
        }
    }
}