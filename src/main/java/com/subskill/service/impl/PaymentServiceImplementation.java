package com.subskill.service.impl;

import com.subskill.models.User;
import com.subskill.service.PaymentService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
@AllArgsConstructor
public class PaymentServiceImplementation implements PaymentService {
    private final UserService userService;


    @Override
    public BigDecimal getTotalPaymentByUserId() {
        User user = userService.getAuthenticatedUser();
        return user.getCart().getTotal();
    }

}
