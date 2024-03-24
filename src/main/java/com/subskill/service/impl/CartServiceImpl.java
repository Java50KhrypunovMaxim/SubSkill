package com.subskill.service.impl;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.exception.CartIsEmptyException;
import com.subskill.exception.CartNotFoundException;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.models.User;
import com.subskill.repository.CartRepository;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.service.CartService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final MicroSkillRepository microSkillRepository;


    @Override
    @Transactional
    public CartDto addMicroSkillToCart(Long microSkillId, Long cardId) {
        MicroSkill microSkill = microSkillRepository.getReferenceById(microSkillId);
        Cart cart = cartRepository.getReferenceById(cardId);
        cart.getListOfMicroSkills().add(microSkill);
        cart.setTotal(cart.getTotal().add(microSkill.getPrice()));
        cartRepository.save(cart);

        return cart.build();
    }

    @Override
    @Transactional
    public void deleteMicroSkillFromCart(Long cart) {
       Cart cartOptional = cartRepository.getReferenceById(cart);
        cartOptional.getListOfMicroSkills().clear();
            cartRepository.save(cartOptional);
    }

   @Transactional(readOnly = true)
    @Override
    public Set<MicroSkillDto> allMicroSkillsInCart(Long cart) {
       Cart cartOptional = cartRepository.getReferenceById(cart);
        return    cartOptional.build().listOfMicroSkills();
   }

//        if (user.getId() == (userId)) {
//            return user.getCart().build().listOfMicroSkills();
//        } else {
//            return Collections.emptySet();
//        }
    }
//        Cart cart = cartRepository.findById(userId)
//                .orElseThrow(CartIsEmptyException::new);
//        if (cart.getListOfMicroSkills().isEmpty()) {
//            throw new MicroSkillNotFoundException();
//        }
//        return cart.build().listOfMicroSkills();
//    }


