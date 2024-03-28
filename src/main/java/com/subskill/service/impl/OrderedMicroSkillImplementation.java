package com.subskill.service.impl;

import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import com.subskill.models.OrderedMicroskill;
import com.subskill.models.User;
import com.subskill.repository.CartRepository;
import com.subskill.repository.OrderedMicroSkillRepository;
import com.subskill.service.OrderedMicroSkillService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class OrderedMicroSkillImplementation implements OrderedMicroSkillService {

    private final OrderedMicroSkillRepository orderedMicroSkillRepository;
    private final UserService userService;
    private final CartRepository cartRepository;


    @Override
    @Transactional
    public void moveFromCartToOrderedMicroSKill() {

        User user = userService.getAuthenticatedUser();
        Cart cart = user.getCart();
        Set<MicroSkill> microSkillsFromCart = new HashSet<>(cart.getSetOfMicroSkills());

        OrderedMicroskill orderedMicroskill = new OrderedMicroskill();
        orderedMicroskill.setUser(user);
        orderedMicroskill.setSetOfMicroSkill(microSkillsFromCart);
        orderedMicroskill.setPurchased(true);
        orderedMicroSkillRepository.save(orderedMicroskill);

        cart.getSetOfMicroSkills().clear();
        cartRepository.save(cart);
    }


    @Override
    public Set<MicroSkill> allOrderedMicroskill() {
        List<OrderedMicroskill> orderedMicroskill = orderedMicroSkillRepository.findAll();
        for (OrderedMicroskill orderedMicroskill1 : orderedMicroskill) {
            if (orderedMicroskill1.isPurchased()) {
                return orderedMicroskill1.getSetOfMicroSkill();
            }
        }
        return Collections.emptySet();
    }
}
