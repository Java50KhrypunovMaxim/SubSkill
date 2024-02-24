package com.subskill.dto;

import java.util.List;

public record CartDto(
        Long userId,
        List<MicroSkillDto> listOfMicroSkills
) {}