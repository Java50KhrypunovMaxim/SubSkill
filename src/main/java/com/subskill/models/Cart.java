package com.subskill.models;

import com.subskill.dto.CartDto;
import com.subskill.dto.MicroSkillDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "microskillCount")
    private Integer microskillCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
    private Guest guest;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "cart_microskill",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "microskill_id")
    )
    private Set<MicroSkill> setOfMicroSkills;

    public CartDto build() {
        Set<MicroSkillDto> listOfMicroSkillDtos = setOfMicroSkills.stream()
                .map(MicroSkill::build)
                .collect(Collectors.toSet());

        return new CartDto(id, user.getId(), total, listOfMicroSkillDtos);
    }

}

