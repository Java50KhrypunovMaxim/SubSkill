package com.subskill.service;

import com.subskill.models.PaymentInfo;
import org.springframework.http.ResponseEntity;

public interface PaymentService {

     ResponseEntity<PaymentInfo> addMicroSkillPriceInCart(Long cartId);

}
