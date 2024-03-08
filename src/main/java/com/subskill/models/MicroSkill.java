package com.subskill.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import jakarta.persistence.*;
import lombok.*;
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
@Builder
public class MicroSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "microskill_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    @Column(name = "creation_date")
    @CreationTimestamp
    private LocalDate creationDate;

    @Column(name = "description")
    private String description;

    @Column(name = "learning_time")
    private String learningTime;

    @ElementCollection(targetClass = Tags.class)
    @CollectionTable(name = "micro_skill_tags",
            joinColumns = @JoinColumn(name = "micro_skill_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "tag")
    private List<Tags> tags;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "popularity")
    @ColumnDefault("0.0")
    private Double popularity;

    @Column(name = "views")
    private Integer views;

    @Column(name = "price")
    private Double price;

    @Column(name = "lesson_count")
    private Integer lessonCount;

    @Column(name = "about_skill")
    private String aboutSkill;

    @Column(name = "last_update_time")
    private LocalDateTime lastUpdateTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "technology_id")
    private Technology technology;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

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

    public MicroSkillDto build() {
        return new MicroSkillDto(name, description, photo, learningTime,
                tags, price, lastUpdateTime, creationDate,
                aboutSkill, level, technology.getId());
    }

    public Double calculateAverageRating() {
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