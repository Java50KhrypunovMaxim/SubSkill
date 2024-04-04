package com.subskill.service.impl;

import com.subskill.models.MicroSkill;
import com.subskill.models.OrderedMicroskill;
import com.subskill.models.User;
import com.subskill.repository.OrderedMicroSkillRepository;
import com.subskill.repository.UserRepository;
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
public class OrderMicroSkillServiceImpl implements OrderedMicroSkillService {

    private final OrderedMicroSkillRepository orderedMicroSkillRepository;
    private final UserService userService;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public void moveFromCartToOrderedMicroSKill() {

        User user = userService.getAuthenticatedUser();
        Set<MicroSkill> microSkillsFromCart = new HashSet<>(user.getCart().getSetOfMicroSkills());
        OrderedMicroskill orderedMicroskill = new OrderedMicroskill();
        orderedMicroskill.setSetOfMicroSkill(microSkillsFromCart);
        orderedMicroSkillRepository.save(orderedMicroskill);
        user.getCart().getSetOfMicroSkills().clear();
        userRepository.save(user);

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
