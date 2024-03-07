package com.subskill.dto;

import static com.subskill.api.ValidationConstants.*;

import java.util.List;

import com.subskill.models.MicroSkill;
import com.subskill.models.Profession;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TechnologyDto(
        @NotEmpty(message = MISSING_TECHNOLOGY_NAME_MESSAGE)
        String name,

        @NotEmpty(message = MISSING_PROFESSION_MESSAGE)
        Profession profession,

        @NotNull(message = MISSING_MICROSKILLS_MESSAGE)
        List<MicroSkill> microSkills) {
}
