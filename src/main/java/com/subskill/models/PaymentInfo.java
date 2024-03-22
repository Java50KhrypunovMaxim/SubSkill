package com.subskill.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Payment_Info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInfo {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Formula("(SELECT c.total FROM cart c WHERE c.cart_id = cart_id)")
//    private BigDecimal totalPayment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

//    @OneToMany(mappedBy = "cart")
//private Set<MicroSkill> microSkillSet;


}