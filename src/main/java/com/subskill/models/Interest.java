package com.subskill.models;

import com.subskill.dto.InterestDto;
import com.subskill.enums.Tags;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interest")
public class Interest {

    @Id
    @Column(name = "interest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ElementCollection(targetClass = Tags.class)
    @CollectionTable(name = "Interest_tags",
            joinColumns = @JoinColumn(name = "interest_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "tag")
    private List<Tags> tags;

    public InterestDto build() {

        return new InterestDto(id, tags);
    }


}
