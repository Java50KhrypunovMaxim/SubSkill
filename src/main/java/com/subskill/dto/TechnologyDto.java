package com.subskill.dto;

import jakarta.validation.constraints.NotEmpty;

import static com.subskill.api.ValidationConstants.MISSING_TECHNOLOGY_NAME_MESSAGE;
import static com.subskill.api.ValidationConstants.PROFESSION_ID_MISSING;

public record TechnologyDto(
        @NotEmpty(message = PROFESSION_ID_MISSING)
        Long id,
        @NotEmpty(message = MISSING_TECHNOLOGY_NAME_MESSAGE)
        String name) {
}