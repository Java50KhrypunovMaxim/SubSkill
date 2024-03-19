package com.subskill.service.impl;

import com.subskill.enums.OrderStatus;
import com.subskill.models.*;
import com.subskill.repository.CartRepository;
import com.subskill.repository.OrderedMicroSkillRepository;
import com.subskill.repository.UserRepository;
import com.subskill.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class PaymentServiceImplementation implements PaymentService {

    private final CartRepository cartRepository;

      private final OrderedMicroSkillRepository orderedMicroSkillRepository;

    @Override
    public ResponseEntity<PaymentInfo> addMicroSkillPriceInCart(Long cartId) {

        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (cartOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Cart cart = cartOptional.get();

        if (cart.getListOfMicroSkills().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();       }
        BigDecimal totalPrice = calculateTotalPrice(cart);
        PaymentInfo paymentInfo = new PaymentInfo(totalPrice);
        OrderedMicroskill orderedMicroskill = new OrderedMicroskill();
        orderedMicroskill.setId(cart.getId());
        orderedMicroskill.setUserId(cart.getUserId());
        orderedMicroskill.setOrderStatus(OrderStatus.STARTED);
        orderedMicroskill.setMicroSkills(cart.getListOfMicroSkills());
        orderedMicroSkillRepository.save(orderedMicroskill);
        return ResponseEntity.ok(paymentInfo);
    }

    private BigDecimal calculateTotalPrice(Cart cart) {
        Set<MicroSkill> microSkills = cart.getListOfMicroSkills();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (MicroSkill microSkill : microSkills) {
            totalPrice = totalPrice.add(microSkill.getPrice());
        }
        return totalPrice;
    }
}
