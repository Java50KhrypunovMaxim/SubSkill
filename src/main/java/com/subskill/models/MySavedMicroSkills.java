package com.subskill.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_saved_microskills ")
@Entity
@Setter
@Builder
public class MySavedMicroSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "mySaved_microskill",
            joinColumns = @JoinColumn(name = "mySaved_microskill"),
            inverseJoinColumns = @JoinColumn(name = "microskill_id")
    )
    private Set<MicroSkill> microSkills;

    private long userId;

}
