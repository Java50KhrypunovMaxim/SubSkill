package com.subskill.dto;

import java.util.Objects;

import static com.subskill.api.ValidationConstants.*;
import com.subskill.enums.Roles;
import com.subskill.enums.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;



public record UserDto (
	@NotEmpty (message= MISSING_PERSON_USERNAME_MESSAGE)
	String username,
	
	@NotEmpty (message= MISSING_PERSON_EMAIL)
	@Pattern(regexp = EMAIL_REGEXP, message= WRONG_EMAIL_FORMAT)
	String email,
	@NotEmpty(message= MISSING_PASSWORD_MESSAGE)
	@Pattern(regexp = PASSWORD_REGEXP, message= WRONG_PASSWORD_CREATION_MESSAGE)
	String password,
	
	@NotNull (message= MISSING_STATUS_MESSAGE)
    Status online,
	
	@NotEmpty (message= MISSING_IMAGE_URL_MESSAGE)
	String imageUrl,
	
	@NotNull (message= MISSING_ROLE_MESSAGE)
	Roles role)
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
		UserDto  other = (UserDto ) obj;
		return Objects.equals(email, other.email);
	}


}
