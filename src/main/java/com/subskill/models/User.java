package com.subskill.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.subskill.dto.UserDto;
import com.subskill.enums.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "status", columnDefinition = "boolean default true")
    private Boolean online;

    @Column(name = "image_url")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'USER'")
    @Column(name = "role", nullable = false)
    private Roles role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SaveMicroskill> saveMicroskills;


    public static User of(UserDto userDto) {
        User user = new User();
        user.username = userDto.username();
        user.password = userDto.password();
        user.email = userDto.email();
        user.online = true;
        user.imageUrl = userDto.imageUrl();
        user.role = userDto.role();
        return user;
    }

    public UserDto build() {
        return new UserDto(username, email,password,
                online,  imageUrl, role);
    }
}

