package com.subskill.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "creationDate")
    @CreationTimestamp
    private LocalDate creationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "learningTime")
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

    @Column(name = "popularity")
    @ColumnDefault("0.0")
    private double popularity;

    @Column(name = "views")
    private int views;

    @Column(name = "price")
    private double price;

    @Column(name = "lessonCount")
    private int lessonCount;
    @Column(name = "aboutSkill")
    private String aboutSkill;
    @Column(name = "lastUpdateTime")
    private LocalDateTime lastUpdateTime;
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
        microSkill.price = microSkillDto.price();
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

    public void calculatePopularity() {
        if (this.rating > 4.0) {
            this.popularity = this.views * 0.3 + this.rating * 2.0;
        } else if (this.rating > 0.0) {
            this.popularity = this.views * 0.3 + this.rating * 0.6;
        }
        if (this.rating == 0.0) {
            this.popularity = 0.0;
        }
    }


}