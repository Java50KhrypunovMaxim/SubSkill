package com.subskill.service.impl;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.UserDto;
import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.repository.CartRepository;
import com.subskill.service.CartService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public CartDto addMicroSkillToCart(MicroSkillDto microSkillDto) {
        MicroSkill microSkill = MicroSkill.of(microSkillDto);
        Optional<Cart> optionalCart = cartRepository.findMicroSkillBy(microSkillDto);
        Cart cart = optionalCart.orElseGet(Cart::new);
        cart.getListOfMicroSkills().add(microSkill);
        cartRepository.save(cart);
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    @Transactional
    public void deleteMicroSkillFromCart(long cart_id) {
        Optional<Cart> cart = cartRepository.findCartByUserId(cart_id);
        if (cart.isPresent()) {
            List<MicroSkill> listOfMicroSkills = cart.get().getListOfMicroSkills();
            Iterator<MicroSkill> iterator = listOfMicroSkills.iterator();
            while (iterator.hasNext()) {
                MicroSkill microSkill = iterator.next();
                if (microSkill.getId() == cart_id) {
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
            UserDto userDto = modelMapper.map(cart.getUser(), UserDto.class);
            CartDto cartDto = new CartDto(userDto, microSkillDto);
            newCart.add(cartDto);

        }
        return newCart;
    }
}