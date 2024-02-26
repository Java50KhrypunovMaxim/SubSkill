package com.subskill.models;

import java.util.Set;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "saved_microskill")
public class SaveMicroskill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_microskill_id", nullable = false)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "save_microskill",
            joinColumns = @JoinColumn(name = "save_microskill_id"),
            inverseJoinColumns = @JoinColumn(name = "microskill_id")
    )
    private Set<MicroSkill> microSkills;
}

