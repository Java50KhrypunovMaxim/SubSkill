package com.subskill.service.impl;

import com.subskill.dto.CartDto;
import com.subskill.exception.CartNotFoundException;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.repository.CartRepository;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final MicroSkillRepository microSkillRepository;

    @Override
    @Transactional
    public CartDto addMicroSkillToCart(Long microSkillId, Long cardId) {
        MicroSkill microSkill = microSkillRepository.findById(microSkillId).orElseThrow(MicroSkillNotFoundException::new);

        Cart cart = cartRepository.findById(cardId).orElseThrow(CartNotFoundException::new);
        if (cart == null) {
            cart = new Cart();
            cart.setId(cardId);
        }
        cart.getListOfMicroSkills().add(microSkill);
        cart.setTotal(cart.getTotal().add(microSkill.getPrice()));
        cartRepository.save(cart);

        return cart.build();
    }

    @Override
    @Transactional
    public void deleteMicroSkillFromCart(Long microSkillId) {
        Optional<Cart> cartOptional = cartRepository.findMicroSkillById(microSkillId);
        cartOptional.ifPresent(cart -> {
            cart.getListOfMicroSkills().removeIf(microSkill -> Objects.equals(microSkill.getId(), microSkillId));
            cartRepository.save(cart);
        });
    }

    @Transactional(readOnly = true)
    @Override
    public CartDto allMicroSkillsInCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(NoSuchElementException::new);
        return cart.build();
    }

}