package subskill.subskill.models;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
	    
	    @Column(name = "imageUrl")
	    private String imageUrl;
	    
	    @Enumerated(EnumType.STRING)
	    @ColumnDefault("ADMIN")
	    @Column(name = "role", nullable = false)
	    private String role;
}
