package com.subskill.dto;

import static com.subskill.api.ValidationConstants.*;

import java.util.List;

import com.subskill.models.MicroSkill;
import com.subskill.models.Profession;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TechnologyDto(
        @NotEmpty(message = PROFESSION_ID_MISSING)
        Long id,
        @NotEmpty(message = MISSING_TECHNOLOGY_NAME_MESSAGE)
        String name) {
}