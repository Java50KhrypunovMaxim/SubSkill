package com.subskill.repository;

import com.subskill.models.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentInfo, Long> {

//    @Query("SELECT c FROM Cart c")
//    List<Cart> findAllCarts();

}