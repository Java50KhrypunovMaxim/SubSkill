package subskill.subskill.dto;
import jakarta.validation.constraints.*;
import static subskill.subskill.api.ValidationConstants.*;

import java.util.Objects;

import subskill.subskill.models.Status;


public record AdminDto(
		
		@NotEmpty (message=MISSING_PERSON_USERNAME_MESSAGE)
		String username,
		
		@NotEmpty (message= MISSING_PASSWORD_MESSAGE)
		@Pattern(regexp = PASSWORD_REGEXP, message=WRONG_PASSWORD_CREATION_MESSAGE)
		String password,
		
		@NotEmpty (message=MISSING_PERSON_EMAIL)
		@Pattern(regexp = EMAIL_REGEXP, message=WRONG_EMAIL_FORMAT)
		String email,
		
		@NotNull (message=MISSING_STATUS_MESSAGE)
		Status status,
		
		@NotEmpty (message=MISSING_IMAGEURL_MESSAGE)
		String imageUrl,
		
		@NotNull (message=MISSING_ROLE_MESSAGE)
		String role	
)
{
	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdminDto other = (AdminDto) obj;
		return Objects.equals(email, other.email);
	}
	
}
