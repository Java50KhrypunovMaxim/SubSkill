package subskill.subskill.models;

import org.hibernate.annotations.ColumnDefault;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import subskill.subskill.dto.AdminDto;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admins")
@Entity
public class Admin {
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
	    
	    
	    public static Admin of(AdminDto adminDto) {
	    	Admin admin = new Admin();
	    	admin.username = adminDto.username();
	    	admin.password = adminDto.password();
	    	admin.email = adminDto.email();
	    	admin.status = adminDto.status();
	    	admin.imageUrl = adminDto.imageUrl();
	    	admin.role= adminDto.role();
	        return admin;
	    }

		public AdminDto build (Admin admin) {
			return new AdminDto(admin.username, admin.password, admin.email,
					admin.status, admin.imageUrl,admin.role);
		}

	}
