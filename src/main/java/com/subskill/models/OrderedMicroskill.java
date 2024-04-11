package com.subskill.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "ordered_microskills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderedMicroskill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_microskill_id")
    private Long orderedMicroSkillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "total")
    private Integer total;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordered_microskill_id")
    private Set<MicroSkill> setOfMicroSkill;

    @Column(name = "is_purchased")
    private boolean isPurchased;

    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Guest guest;

}