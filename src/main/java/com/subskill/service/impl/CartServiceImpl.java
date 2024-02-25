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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
    public void deleteMicroSkillFromCart(long cartId) {
        Optional<Cart> cart = cartRepository.findCartByUserId(cartId);
        if (cart.isPresent()) {
            List<MicroSkill> listOfMicroSkills = cart.get().getListOfMicroSkills();
            Iterator<MicroSkill> iterator = listOfMicroSkills.iterator();
            while (iterator.hasNext()) {
                MicroSkill microSkill = iterator.next();
                if (microSkill.getId() == cartId) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    @Transactional(readOnly = true)
    @Override
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