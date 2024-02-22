package com.subskill.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import static com.subskill.api.ValidationConstants.*;

public record UserDtoPassword (@NotEmpty(message= MISSING_PASSWORD_MESSAGE)
                              @Pattern(regexp = PASSWORD_REGEXP, message= WRONG_PASSWORD_CREATION_MESSAGE)
                              String password){
}
