package com.subskill.service.impl;

import com.subskill.models.Cart;
import com.subskill.repository.CartRepository;
import com.subskill.repository.PaymentRepository;
import com.subskill.service.PaymentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentServiceImplementation implements PaymentService {
    private final PaymentRepository paymentRepository;



    @Override
    public BigDecimal getTotalPaymentByUserId(Long userId) {
    List<Cart> cart = paymentRepository.findAllCarts();
    BigDecimal total =null;
    for( Cart cart1 : cart) {
        if(cart1.getUserId().equals(userId)){
            total = cart1.getTotal();
        }
    }
    return total;
    }

}
