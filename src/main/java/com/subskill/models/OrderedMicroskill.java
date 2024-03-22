package com.subskill.models;

import com.subskill.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Ordered_Microskill")
@Entity
@Setter
public class OrderedMicroskill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "orderStatus")
   private OrderStatus orderStatus;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "ordered_microskills_microskills",
            joinColumns = @JoinColumn(name = "ordered_microskill_id"),
            inverseJoinColumns = @JoinColumn(name = "microskill_id")
    )
    private Set<MicroSkill> microSkills;


}
