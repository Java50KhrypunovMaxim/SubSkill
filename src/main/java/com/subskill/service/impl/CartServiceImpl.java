package com.subskill.service.impl;

import com.subskill.dto.CartDto;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.repository.CartRepository;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.service.CartService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final MicroSkillRepository microSkillRepository;
    private final UserService userService;

    @Override
    @Transactional
    @CachePut(value = "cart", key = "#microSkillId")
    public CartDto addMicroSkillToCart(Long microSkillId) {
        try {
            long userId = userService.getAuthenticatedUser().getId();
            MicroSkill microSkill = microSkillRepository.findById(microSkillId)
                    .orElseThrow(MicroSkillNotFoundException::new);
            Cart cart = cartRepository.findByUserId(userId)
                    .orElseGet(() -> {
                        Cart newCart = new Cart();
                        newCart.setUserId(userId);
                        return cartRepository.save(newCart);
                    });
            cart.getListOfMicroSkills().add(microSkill);
            cartRepository.save(cart);
            return cart.build();
        } catch (UsernameNotFoundException e) {
            e.getStackTrace();

        }
        return new CartDto(1L, List.of());
    }


    @CacheEvict(value = "cart", key = "#microSkillId", cacheManager = "objectCacheManager")
    @Override
    @Transactional
    public void deleteMicroSkillFromCart(Long microSkillId) {
        Optional<Cart> cartOptional = cartRepository.findBMicroSkillById(microSkillId);
        cartOptional.ifPresent(cart -> {
            cart.getListOfMicroSkills().removeIf(microSkill -> Objects.equals(microSkill.getId(), microSkillId));
            cartRepository.save(cart);
        });
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "carts")
    public CartDto allMicroSkillsInCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId).orElseThrow(NoSuchElementException::new);
        return cart.build();
    }

}