package com.subskill.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import static com.subskill.api.ValidationConstants.*;

public record UserProfileDto(
        //	@NotEmpty (message= MISSING_PERSON_USERNAME_MESSAGE)
                             String username,
//                             @NotEmpty(message = MISSING_PERSON_EMAIL)
                             @Pattern(regexp = EMAIL_REGEXP, message = WRONG_EMAIL_FORMAT)
                             String email,                   


//	@NotEmpty (message= MISSING_IMAGE_URL_MESSAGE)
                             String title,

//	@NotNull (message= MISSING_ROLE_MESSAGE)
                             String interest) {
}
