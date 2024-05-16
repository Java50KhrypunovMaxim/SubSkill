package com.subskill.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.subskill.dto.UserDto;
import com.subskill.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(name = "username")
    private String username;

    @Column(name = "job_title")
    private String jobTitle;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "email",unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    @Column(name = "role")
    private Roles role;

    @Column(name = "token")
    private String token;

    @Column(name = "tokenCreationDate", columnDefinition = "TIMESTAMP")
    private LocalDateTime tokenCreationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MicroSkill> microSkill;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<SaveMicroskill> savedMicroskills;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<MySavedMicroSkills> mySavedMicroSkills;

    @ManyToMany(mappedBy = "userInterest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Interest> interests;


    public static User of(UserDto userDto) {
        User user = new User();
        user.username = userDto.username();
        user.password = userDto.password();
        user.email = userDto.email();
        user.role = userDto.role();
        return user;
    }


    public UserDto build() {
        return new UserDto(username, jobTitle, email, password, role);
    }
}

