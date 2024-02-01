package com.subskill.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import com.subskill.dto.ArticleDto;
import com.subskill.dto.TechnologyDto;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "technologies")
@Entity
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "profession_id")
    private Profession profession;

    @OneToMany(mappedBy = "technology")
    private List<MicroSkill> microSkills;
    
    public static Technology of(TechnologyDto technologyDto) {
    	Technology technology = new Technology();
        return null;
    }
}