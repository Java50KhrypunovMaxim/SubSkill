package com.subskill.service;

import com.subskill.models.MicroSkill;
import com.subskill.models.OrderedMicroskill;
import com.subskill.models.User;

import java.util.List;
import java.util.Set;

public interface OrderedMicroSkillService {
    void moveFromCartToOrderedMicroSKill(long userId);

    List<OrderedMicroskill> allOrderedMicroskill();

    void processPayment(User user, Set<MicroSkill> microskills);
}
