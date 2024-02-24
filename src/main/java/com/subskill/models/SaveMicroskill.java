package com.subskill.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "save_microskill")
public class SaveMicroskill {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "save_microskill_id", nullable = false)
    private Long id;

	@ManyToOne
    @JoinColumn(name = "user")
    private User user;

    @ManyToMany
    @JoinColumn(name = "microSkill")
    private MicroSkill microSkills;
    
    public SaveMicroskill(User user, MicroSkill microSkills) {
        this.user = user;
        this.microSkills = microSkills;
    }
}

