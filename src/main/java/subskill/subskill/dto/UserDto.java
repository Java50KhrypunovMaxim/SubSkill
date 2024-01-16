package subskill.subskill.dto;

import static subskill.subskill.api.ValidationConstants.*;
import static subskill.subskill.api.ValidationConstants.MISSING_IMAGEURL_MESSAGE;
import static subskill.subskill.api.ValidationConstants.MISSING_PASSWORD_MESSAGE;
import static subskill.subskill.api.ValidationConstants.MISSING_PERSON_EMAIL;
import static subskill.subskill.api.ValidationConstants.MISSING_PERSON_USERNAME_MESSAGE;
import static subskill.subskill.api.ValidationConstants.MISSING_ROLE_MESSAGE;
import static subskill.subskill.api.ValidationConstants.MISSING_STATUS_MESSAGE;
import static subskill.subskill.api.ValidationConstants.PASSWORD_REGEXP;
import static subskill.subskill.api.ValidationConstants.WRONG_EMAIL_FORMAT;
import static subskill.subskill.api.ValidationConstants.WRONG_PASSWORD_CREATION_MESSAGE;

import java.util.Objects;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import subskill.subskill.models.Roles;
import subskill.subskill.models.Status;

public record UserDto (
	@NotEmpty (message=MISSING_PERSON_USERNAME_MESSAGE)
	String username,
	
	@NotEmpty (message= MISSING_PASSWORD_MESSAGE)
	@Pattern(regexp = PASSWORD_REGEXP, message=WRONG_PASSWORD_CREATION_MESSAGE)
	String password,
	
	@NotEmpty (message=MISSING_PERSON_EMAIL)
	@Pattern(regexp = EMAIL_REGEXP, message=WRONG_EMAIL_FORMAT)
	String email,
	
	@NotEmpty (message=MISSING_NICKNAME_MESSAGE)
	String nickname,
	
	@NotNull (message=MISSING_STATUS_MESSAGE)
	Status status,
	
	@NotEmpty (message=MISSING_IMAGEURL_MESSAGE)
	String imageUrl,
	
	@NotNull (message=MISSING_ROLE_MESSAGE)
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
