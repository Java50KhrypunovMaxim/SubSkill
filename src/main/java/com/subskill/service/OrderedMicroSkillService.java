package com.subskill.service;

import com.subskill.models.OrderedMicroskill;

import java.util.List;

public interface OrderedMicroSkillService {
    void moveFromCartToOrderedMicroSKill(long userId);
    List<OrderedMicroskill> allOrderedMicroskill();
}
