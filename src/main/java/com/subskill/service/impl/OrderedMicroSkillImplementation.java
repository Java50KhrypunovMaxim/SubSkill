package com.subskill.service.impl;

import com.subskill.enums.OrderStatus;
import com.subskill.exception.CartIsEmptyException;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.exception.NotFoundException;
import com.subskill.exception.RegistrationUserNotFoundException;
import com.subskill.models.Cart;
import com.subskill.models.OrderedMicroskill;
import com.subskill.models.User;
import com.subskill.repository.CartRepository;
import com.subskill.repository.OrderedMicroSkillRepository;
import com.subskill.repository.UserRepository;
import com.subskill.service.OrderedMicroSkillService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OrderedMicroSkillImplementation implements OrderedMicroSkillService {

    private final OrderedMicroSkillRepository orderedMicroSkillRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public void moveFromCartToOrderedMicroSKill(long userId) {
//
//        Optional<OrderedMicroskill> orderedMicroskill = orderedMicroSkillRepository.findByUserId(userId);
//
//        orderedMicroskill.get().setOrderedMicroskill();
//
//
//        orderedMicroskill.getOrderedMicroskill().addAll(cart.getListOfMicroSkills());
//
//        cart.getListOfMicroSkills().clear();
//        cartRepository.save(cart);
//
//        orderedMicroSkillRepository.save(orderedMicroskill);
    }


    @Override
    public List<OrderedMicroskill> allOrderedMicroskill() {
        return orderedMicroSkillRepository.findAll();
    }
}
