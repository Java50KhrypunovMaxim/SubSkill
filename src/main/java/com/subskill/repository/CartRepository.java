package com.subskill.repository;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findMicroSkillBy(MicroSkillDto microSkillDto);
    Cart findCartByUserId(long userId);


}
