package com.subskill.service.impl;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.models.User;
import com.subskill.repository.CartRepository;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.UserRepository;
import com.subskill.service.CartService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final MicroSkillRepository microSkillRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    @Transactional
    @CachePut(value = "cart", key = "#microSkillId")
    public CartDto addMicroSkillToCart(long microSkillId) {
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
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    @Transactional
    @CacheEvict(value = "cart", key = "#cartId", cacheManager = "objectCacheManager")
    public void deleteMicroSkillFromCart(long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Set<MicroSkill> listOfMicroSkills = cart.getListOfMicroSkills();
            Iterator<MicroSkill> iterator = listOfMicroSkills.iterator();
            while (iterator.hasNext()) {
                MicroSkill microSkill = iterator.next();
                if (microSkill.getId() == cartId) {
                    iterator.remove();
                    break;
                }
            }
            cart.setListOfMicroSkills(listOfMicroSkills);
            cartRepository.save(cart);
        }

    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "carts")
    public List<CartDto> allMicroSkillsInCart() {
        List<Cart> carts = cartRepository.findAll();
        List<CartDto> newCart = new ArrayList<>();
        for (Cart cart : carts) {
            List<MicroSkillDto> microSkillDto = cart.getListOfMicroSkills().stream()
                    .map(microSkill -> modelMapper.map(microSkill, MicroSkillDto.class))
                    .toList();
            User user = userRepository.findById(cart.getUserId())
                    .orElseThrow(NoUserInRepositoryException::new);
            CartDto cartDto = new CartDto(user.getId(), microSkillDto);
            newCart.add(cartDto);
        }
        return newCart;
    }

}