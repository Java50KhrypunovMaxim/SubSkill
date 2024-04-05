package com.subskill.models;

import com.subskill.dto.InterestDto;
import com.subskill.enums.Tags;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    public Tags name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "interest_id"),

            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userInterest = new ArrayList<>();


    public InterestDto build() {

        return new InterestDto(id,name);
    }


}
