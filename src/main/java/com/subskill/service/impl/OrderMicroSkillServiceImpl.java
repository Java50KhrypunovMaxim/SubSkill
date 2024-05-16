package com.subskill.service.impl;

import com.subskill.dto.MicroSkillDto;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.models.MicroSkill;
import com.subskill.models.MySavedMicroSkills;
import com.subskill.models.OrderedMicroskill;
import com.subskill.models.User;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.MySavedMicroSkillsRepository;
import com.subskill.repository.OrderedMicroSkillRepository;
import com.subskill.repository.UserRepository;
import com.subskill.service.OrderedMicroSkillService;
import com.subskill.service.SendMailService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
@Slf4j
public class OrderMicroSkillServiceImpl implements OrderedMicroSkillService {

    private final OrderedMicroSkillRepository orderedMicroSkillRepository;
    private final MicroSkillRepository microSkillRepository;
    private final MySavedMicroSkillsRepository mySavedMicroSkillsRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final SendMailService sendMailService;


    @Override
    @Transactional
    public void moveFromCartToOrderedMicroSKill() {
        User user = userService.getAuthenticatedUser();
        Set<MicroSkill> microSkillsFromCart = new HashSet<>(user.getCart().getSetOfMicroSkills());
        OrderedMicroskill orderedMicroskill = new OrderedMicroskill();
        orderedMicroskill.setSetOfMicroSkill(microSkillsFromCart);
        MySavedMicroSkills mySavedMicroSkills = new MySavedMicroSkills();
        mySavedMicroSkills.setMicroSkills(microSkillsFromCart);
        mySavedMicroSkills.setUser(user);
        mySavedMicroSkillsRepository.save(mySavedMicroSkills);
        orderedMicroSkillRepository.save(orderedMicroskill);
        user.getCart().getSetOfMicroSkills().clear();
        userRepository.save(user);
        double total = 0.0;
        StringBuilder microSkillNames = new StringBuilder();
        for (MicroSkill microSkill : microSkillsFromCart) {
            total += microSkill.getPrice().doubleValue();
            microSkillNames.append(microSkill.getName()).append(", ");
        }
        String subject = "Order Confirmation from SubSkill";
        String body = "Hello " + user.getUsername() + ",\n\n"
                      + "Thank you for your order!\n"
                      + "You have ordered the following micro skills:\n"
                      + "Total: $" + total + "\n"
                      + "Micro skills: " + microSkillNames.toString() + "\n\n"
                      + "We will process your order shortly.\n\n"
                      + "Best regards,\n"
                      + "Team SubSkill";
        log.debug(" subject  {} +  body {} + user email {}",subject,body,user.getEmail());
        sendMailService.constructEmail(subject, body, user);
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

    @Override
    public MicroSkillDto singleMicroSkillBuy(long microskillId) {
        MicroSkill microSkill = microSkillRepository.findById(microskillId).orElseThrow(MicroSkillNotFoundException::new);
        return  microSkill.build();
    }
}
