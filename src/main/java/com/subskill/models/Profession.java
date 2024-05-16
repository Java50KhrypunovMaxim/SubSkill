package com.subskill.models;

import com.subskill.dto.ProfessionDto;
import com.subskill.enums.Tags;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "professions")
@Entity
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profession_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tags name;

    @ManyToMany
    @JoinTable(
            name = "profession_technologies",
            joinColumns = @JoinColumn(name = "profession_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private List<Technology> technologies;


    public ProfessionDto build() {
        return new ProfessionDto(id, name);
    }
}