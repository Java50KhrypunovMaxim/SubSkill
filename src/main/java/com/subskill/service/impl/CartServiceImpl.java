package com.subskill.service.impl;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.exception.UserNotFoundException;
import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.models.User;
import com.subskill.repository.CartRepository;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.UserRepository;
import com.subskill.service.CartService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final MicroSkillRepository microSkillRepository;
    private final UserService userService;


    @Override
    @Transactional
    public CartDto addMicroSkillToCart(Long microSkillId) {
        Optional<MicroSkill> microSkill = microSkillRepository.findById(microSkillId);
        if (microSkill.isEmpty()) {
            throw new MicroSkillNotFoundException();
        }
        User user = userService.getAuthenticatedUser();
        user.getCart().getListOfMicroSkills().size();
        user.getCart().getListOfMicroSkills().add(microSkill.get());
        user.getCart().setTotal(user.getCart().getTotal().add(microSkill.get().getPrice()));

        return user.getCart().build();
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
       Cart cartNotOptional = cartRepository.getReferenceById(cart);
        return    cartNotOptional.build().listOfMicroSkills();
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


