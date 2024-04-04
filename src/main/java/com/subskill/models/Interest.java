package com.subskill.models;

import com.subskill.dto.InterestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "interest")
public class Interest {
//
//    @Id
//    @Column(name = "interest_id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    public Long id;
//
//    @OneToMany(mappedBy = "interest")
//    private List<UserProfile> userInterests = new ArrayList<>();
//
//    @Column(name = "name")
//    public String name;
//
//    public InterestDto build() {
//
//        return new InterestDto(id,userInterests, name);
//    }

    @Id
    @Column(name = "interest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_interest",
            joinColumns = @JoinColumn(name = "interest_id"),

            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userInterest = new ArrayList<>();

    public Interest(String name) {
        this.name = name;
        this.userInterest = new ArrayList<>();
    }
    public InterestDto build() {

        return new InterestDto(id, name);
    }


}
