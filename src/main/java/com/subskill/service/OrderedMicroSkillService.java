package com.subskill.service;

import com.subskill.models.MicroSkill;

import java.util.Set;

public interface OrderedMicroSkillService {
    void moveFromCartToOrderedMicroSKill();

    Set<MicroSkill> allOrderedMicroskill();

}
