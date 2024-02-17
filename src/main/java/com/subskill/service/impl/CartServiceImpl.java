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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    @Override
    public CartDto addMicroSkillToCart(MicroSkillDto microSkillDto) {

        MicroSkill microSkill = MicroSkill.of(microSkillDto);
        Cart cart = cartRepository.findMicroSkillBy(microSkillDto);
        cart.getListOfMicroSkills().add(microSkill);
        cartRepository.save(cart);
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public void deleteMicroSkillFromCart(long id) {
        Cart cart = cartRepository.findCartByUserId(id);
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