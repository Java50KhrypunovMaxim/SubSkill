package com.subskill.repository;

import com.subskill.models.Cart;
import com.subskill.models.MicroSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByListOfMicroSkills(MicroSkill microSkill);

    Optional<Cart> findCartByUserId(long userId);


}
