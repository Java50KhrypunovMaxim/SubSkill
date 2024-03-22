package com.subskill.models;

import com.subskill.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Ordered_Microskill")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedMicroskill {

    @Id
    @Column(name = "ordered_microskill_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderedMicroSkillId;

    @Column(name = "order_Status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.STARTED;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
//
//    @OneToMany(mappedBy = "orderedMicroskill", cascade = CascadeType.ALL)
//    private Set<MicroSkill> orderedMicroskill;


}