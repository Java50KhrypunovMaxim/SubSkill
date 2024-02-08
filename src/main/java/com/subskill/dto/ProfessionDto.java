package com.subskill.dto;

import jakarta.validation.constraints.NotEmpty;

import static com.subskill.api.ValidationConstants.*;

public record ProfessionDto(
        @NotEmpty(message = PROFESSION_ID_MISSING)
        Long id,
        @NotEmpty(message = PROFESSION_NAME_MISSING)
        String name) {
}
