package com.subskill.repository;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    CartDto save(MicroSkillDto microSkillDto);
    Cart findMicroSkillByUser(long id);
}
