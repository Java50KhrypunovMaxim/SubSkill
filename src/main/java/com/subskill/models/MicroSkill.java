package com.subskill.models;

import com.subskill.dto.MicroSkillDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "micro_skills")
@Entity
public class MicroSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String photo;


    @Column(nullable = false)
    private double rating;

    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;

    @OneToMany(mappedBy = "microSkill")
    private List<Article> articles;
    public static MicroSkill of(MicroSkillDto microSkillDto) {
        MicroSkill microSkill = new MicroSkill();
        microSkill.name = microSkillDto.microSkillname();
        microSkill.photo = microSkillDto.microSkillphoto();
        microSkill.rating = microSkillDto.microSkillrating();
        microSkill.technology = microSkillDto.technologyId();
        return microSkill;
    }

    public MicroSkillDto build() {
        return new MicroSkillDto(name, rating, photo,technology);
    }

}