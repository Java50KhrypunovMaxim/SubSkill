package subskill.subskill.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import subskill.subskill.dto.UserDto;


import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    private long id;
    @Column(name = "username",nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "email",nullable = false,unique = true)
    private String email;
    @Column(name = "nickname",nullable = false,unique = true)
    private String nickname;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("ONLINE")
    @Column(name = "status")
    private Status status;
    @Column(name = "imageUrl")
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    @ColumnDefault("USER")
    @Column(name = "role", nullable = false)
    private Roles role;
    
    
    public static User of(UserDto userDto) {
        User user = new User();
        user.username = userDto.username();
        user.password = userDto.password();
        user.email = userDto.email();
        user.nickname = userDto.nickname();
        user.status = userDto.status();
        user.imageUrl = userDto.imageUrl();
        user.role= userDto.role();
        return user;
    }

	public UserDto build (User user) {
		return new UserDto(user.username, user.password, user.email, user.nickname,
				user.status, user.imageUrl, user.role);
	}

}
