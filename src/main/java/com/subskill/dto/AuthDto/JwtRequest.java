package com.subskill.dto.AuthDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import static com.subskill.api.ValidationConstants.*;

@Builder
public record JwtRequest (@NotNull(message = USER_NOT_FOUND) String username,
                          @NotNull(message = PASSWORD_NOT_FOUND) String password) {


}
