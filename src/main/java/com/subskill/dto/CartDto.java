package com.subskill.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import static com.subskill.api.ValidationConstants.PROFESSION_ID_MISSING;

public record CartDto(
        @NotEmpty(message = PROFESSION_ID_MISSING)
        Long id,
        Long userId,
        java.math.BigDecimal total,
        List<MicroSkillDto> listOfMicroSkills
) {}