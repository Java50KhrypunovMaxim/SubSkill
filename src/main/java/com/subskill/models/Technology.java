package com.subskill.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import com.subskill.dto.ArticleDto;
import com.subskill.dto.TechnologyDto;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "technologies")
@Entity
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technology_id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @OneToMany(mappedBy = "technology")
    @Column(name = "micro_skills")
    private List<MicroSkill> microSkills;

    @Transient
    private Long professionId;

    public static Technology of(TechnologyDto technologyDto) {
        Technology technology = new Technology();
        technology.name = technologyDto.name();
        return technology;
    }

    public TechnologyDto build() {
        return new TechnologyDto(name);
    }
}