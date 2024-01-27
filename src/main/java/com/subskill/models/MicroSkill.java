package com.subskill.models;

import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "micro_skills")
@Entity
public class MicroSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "creationDate")
    private LocalDate creationDate;
    @Column(name = "description")
    private String description;
    @Column(name = "learningtime")
    private String learningtime;
    @Column(name = "tags")
    private Tags tags;
    @Column(name = "level")
    private Level level;



    @Column(name = "rating")
    private double rating;

    @Column(name = "views")
    private int views;
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



}