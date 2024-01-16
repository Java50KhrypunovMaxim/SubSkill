package subskill.subskill.models;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@Table(name = "admins")
public class Admins {
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
	    
	    @Enumerated(EnumType.STRING)
	    @ColumnDefault("ONLINE")
	    @Column(name = "status")
	    private Status status;
	    
	    @Column(name = "image_Url")
	    private String imageUrl;
	    
	    @Enumerated(EnumType.STRING)
	    @ColumnDefault("ADMIN")
	    @Column(name = "role", nullable = false)
	    private Roles role;
}
