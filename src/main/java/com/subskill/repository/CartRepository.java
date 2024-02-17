package com.subskill.repository;

import com.subskill.dto.MicroSkillDto;
import com.subskill.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart findMicroSkillBy(MicroSkillDto microSkillDto);

    Cart findCartByUserId(long userId);


}
