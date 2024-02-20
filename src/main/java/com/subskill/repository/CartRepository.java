package com.subskill.repository;

import com.subskill.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

//    Optional<Cart> findMicroSkillBy(MicroSkillDto microSkillDto);

    Optional<Cart> findCartByUserId(long userId);


}
