package com.subskill.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "micro_skills")
@Entity
@Setter
public class MicroSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "microskill_id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "creationdate")
    @CreationTimestamp
    private LocalDate creationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "learningtime")
    private String learningTime;

    @ElementCollection(targetClass = Tags.class)
    @CollectionTable(name = "micro_skill_tags", joinColumns = @JoinColumn(name = "micro_skill_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "tag")
    private List<Tags> tags;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Column(name = "rating")
    private double rating;

    @Column(name = "views")
    private int views;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;

    @JsonIgnore
    @OneToMany(mappedBy = "microSkill", cascade = CascadeType.ALL)
    private List<Article> articles;

    @OneToMany(mappedBy = "microSkill", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public static MicroSkill of(MicroSkillDto microSkillDto) {
        MicroSkill microSkill = new MicroSkill();
        microSkill.name = microSkillDto.name();
        microSkill.photo = microSkillDto.photo();
        microSkill.creationDate = LocalDate.now();
        microSkill.description = microSkillDto.description();
        microSkill.learningTime = microSkillDto.learningTime();
        microSkill.tags = microSkillDto.tags();
        microSkill.level = microSkillDto.level();
        return microSkill;
    }
    
    public double calculateAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0; 
        }
        double totalRating = 0.0;
        for (Review review : reviews) {
            totalRating += review.getRating();
        }
        return totalRating / reviews.size();
    }


}