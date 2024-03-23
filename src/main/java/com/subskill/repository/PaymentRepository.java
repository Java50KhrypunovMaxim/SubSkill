package com.subskill.repository;

import com.subskill.models.Cart;
import com.subskill.models.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentInfo, Long> {

//    @Query("SELECT c FROM Cart c")
//    List<Cart> findAllCarts();

}