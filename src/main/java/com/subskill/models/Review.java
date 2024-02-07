package com.subskill.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "microskill_id")
    private MicroSkill microSkill;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}