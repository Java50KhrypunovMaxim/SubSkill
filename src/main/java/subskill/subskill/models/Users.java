package subskill.subskill.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class Users {
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

}
