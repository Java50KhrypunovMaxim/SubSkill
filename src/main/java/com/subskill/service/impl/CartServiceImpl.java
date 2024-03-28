package com.subskill.service.impl;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
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

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final MicroSkillRepository microSkillRepository;
    private final UserService userService;


    @Override
    public CartDto addMicroSkillToCart(Long microSkillId) {
        Optional<MicroSkill> microSkill = microSkillRepository.findById(microSkillId);
        if (microSkill.isEmpty()) {
            throw new MicroSkillNotFoundException();
        }
        User user = userService.getAuthenticatedUser();

        Optional<Cart> cart = cartRepository.findById(user.getCart().getId());
        Cart userCart = cart.orElseThrow(CartNotFoundException::new);
        if (userCart.getTotal() == null) {
            userCart.setTotal(BigDecimal.ZERO);
        }
        userCart.getSetOfMicroSkills().add(microSkill.get());
        userCart.setTotal(userCart.getTotal().add(microSkill.get().getPrice()));
        user.setCart(userCart);
        cartRepository.save(userCart);
        return user.getCart().build();
    }

    @Override
    @Transactional
    public void deleteMicroSkillFromCart(Long microskillId) {
        User user = userService.getAuthenticatedUser();
        Cart cart = cartRepository.findById(user.getCart().getId()).orElseThrow(CartNotFoundException::new);
        Set<MicroSkill> microSkills = cart.getSetOfMicroSkills();
        Optional<MicroSkill> microSkillToRemove = microSkills.stream()
                .filter(microSkill -> microSkill.getId().equals(microskillId))
                .findFirst();
        microSkillToRemove.ifPresent(microSkills::remove);
        BigDecimal newTotal = BigDecimal.ZERO;
        for (MicroSkill microSkill : microSkills) {
            newTotal = newTotal.add(microSkill.getPrice());
        }
        cart.setTotal(newTotal);
        cartRepository.save(cart);
    }
@Override
@Transactional
public void deleteAllMicroSKillFromCart( ) {
        User user = userService.getAuthenticatedUser();
    Cart cartOptional = cartRepository.findById(user.getCart().getId()).orElseThrow(CartNotFoundException::new);
    cartOptional.getSetOfMicroSkills().clear();
    cartOptional.setTotal(BigDecimal.ZERO);
    cartRepository.save(cartOptional);
}

    @Override
    public BigDecimal totalMoneyForMicroSkill() {
        User user = userService.getAuthenticatedUser();
        return user.getCart().getTotal();
    }

    @Transactional(readOnly = true)
    @Override
    public Set<MicroSkillDto> allMicroSkillsInCart() {
        User user = userService.getAuthenticatedUser();
        Optional<Cart> cartNotOptional = cartRepository.findById((user.getCart().getId()));
        user.setCart(cartNotOptional.get());

        return user.getCart().build().listOfMicroSkills();
    }

}

