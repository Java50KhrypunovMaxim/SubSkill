package com.subskill.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import static com.subskill.api.ValidationConstants.*;


public record InterestDto(@NotEmpty(message = INTEREST_ID_MISSING)
                          Long id,
//                          @NotEmpty(message = USER_NOT_FOUND)
                          List<UserDto> user,
                          @NotEmpty(message = INTEREST_NAME_NOT_FOUND)
                          String name){
}
