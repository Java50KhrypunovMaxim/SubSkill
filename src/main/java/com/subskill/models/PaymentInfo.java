package com.subskill.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Payment_Info")
@Entity
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total")
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public PaymentInfo(BigDecimal totalPrice) {
        this.total = totalPrice;
    }
}
