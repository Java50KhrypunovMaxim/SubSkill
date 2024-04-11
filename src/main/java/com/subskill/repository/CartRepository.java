package com.subskill.repository;

import com.subskill.models.Cart;
import com.subskill.models.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(long userId);
    Optional<Cart> findById(long cardId);
    Optional<Cart> findGuest(Guest guestId);

}
