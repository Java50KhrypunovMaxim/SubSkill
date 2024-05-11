package com.subskill.dto.AuthDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import static com.subskill.api.ValidationConstants.*;

public record RegisteredUserDto (
        @NotNull(message = USER_NOT_FOUND)
        String username,
        String jobTitle,
        @NotNull(message = PASSWORD_NOT_FOUND) String password,
        @Pattern(regexp = EMAIL_REGEXP, message= WRONG_EMAIL_FORMAT)
        String email

) {
}