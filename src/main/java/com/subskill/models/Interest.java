package com.subskill.models;

import com.subskill.dto.InterestDto;
import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "interest",
            joinColumns = @JoinColumn(name = "interest_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    public List<User> user;

    @Column(name = "name")
    public String name;

    public InterestDto build() {
        List<UserDto> listOfUserDto = user.stream()
                .map(User::build)
                .toList();
        return new InterestDto(id,listOfUserDto,name );
    }


}
