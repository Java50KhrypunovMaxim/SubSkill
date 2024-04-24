package com.subskill.dto.AuthDto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import static com.subskill.api.ValidationConstants.TOKEN_NOT_FOUND;

@Builder
public record JwtResponse (@NotNull(message = TOKEN_NOT_FOUND) String token) {

}
