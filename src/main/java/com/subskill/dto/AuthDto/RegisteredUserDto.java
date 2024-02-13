package com.subskill.dto.AuthDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.subskill.api.ValidationConstants.*;

public record RegisteredUserDto (
        @NotNull(message = USER_NOT_FOUND) String username,
        @NotNull(message = PASSWORD_NOT_FOUND) String password,
        @NotNull(message = CONFIRMED_PASSWORD_NOT_FOUND) String confirmedPassword,
        @NotEmpty(message= MISSING_PERSON_EMAIL)
        @Pattern(regexp = EMAIL_REGEXP, message= WRONG_EMAIL_FORMAT)
        String email
) {
}