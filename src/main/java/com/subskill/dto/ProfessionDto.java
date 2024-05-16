package com.subskill.dto;

import com.subskill.enums.Tags;
import jakarta.validation.constraints.NotEmpty;

import static com.subskill.api.ValidationConstants.PROFESSION_ID_MISSING;
import static com.subskill.api.ValidationConstants.PROFESSION_NAME_MISSING;

public record ProfessionDto(
        @NotEmpty(message = PROFESSION_ID_MISSING)
        Long id,
        @NotEmpty(message = PROFESSION_NAME_MISSING)
        Tags name) {
}
