package com.subskill.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.subskill.dto.ArticleDto;
import com.subskill.dto.EditMicroSkillDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.enums.Level;
import com.subskill.enums.Tags;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.lang.reflect.Constructor;
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

    @UpdateTimestamp
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
        microSkill.id = microSkillDto.id();
        microSkill.name = microSkillDto.name();
        microSkill.photo = microSkillDto.photo();
        microSkill.creationDate = LocalDate.now();
        microSkill.description = microSkillDto.description();
        microSkill.learningTime = microSkillDto.learningTime();
        microSkill.tags = microSkillDto.tags();
        microSkill.level = microSkillDto.level();
        microSkill.views = microSkillDto.views();
        microSkill.price = microSkillDto.price();
        microSkill.rating = microSkillDto.rating();
        return microSkill;
    }

    public MicroSkillDto build() {
        return new MicroSkillDto(id,name, description, photo, learningTime,
                tags, price, lastUpdateTime, rating,creationDate,
                aboutSkill, level,views, technology.getId());
    }

    public void updateMicroSkillFromDto(MicroSkill microSkill, EditMicroSkillDto dto) {
        microSkill.setName(dto.name());
        microSkill.setDescription(dto.description());
        microSkill.setPhoto(dto.photo());
        microSkill.setLearningTime(dto.learningTime());
        microSkill.setAboutSkill(dto.aboutSkill());
        microSkill.setLessonCount(dto.lessonCount());
        microSkill.setTags(dto.tags());
        microSkill.setLevel(dto.level());
        microSkill.setPrice(dto.price());
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