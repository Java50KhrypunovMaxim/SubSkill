package com.subskill.dto;

import java.util.Objects;

import com.subskill.api.ValidationConstants;
import com.subskill.models.Roles;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDto (
	@NotEmpty (message= ValidationConstants.MISSING_PERSON_USERNAME_MESSAGE)
	String username,
	
	@NotEmpty (message= ValidationConstants.MISSING_PASSWORD_MESSAGE)
	@Pattern(regexp = ValidationConstants.PASSWORD_REGEXP, message= ValidationConstants.WRONG_PASSWORD_CREATION_MESSAGE)
	String password,
	
	@NotEmpty (message= ValidationConstants.MISSING_PERSON_EMAIL)
	@Pattern(regexp = ValidationConstants.EMAIL_REGEXP, message= ValidationConstants.WRONG_EMAIL_FORMAT)
	String email,
	
	@NotEmpty (message= ValidationConstants.MISSING_NICKNAME_MESSAGE)
	String nickname,
	
	@NotNull (message= ValidationConstants.MISSING_STATUS_MESSAGE)
	boolean online,
	
	@NotEmpty (message= ValidationConstants.MISSING_IMAGEURL_MESSAGE)
	String imageUrl,
	
	@NotNull (message= ValidationConstants.MISSING_ROLE_MESSAGE)
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
