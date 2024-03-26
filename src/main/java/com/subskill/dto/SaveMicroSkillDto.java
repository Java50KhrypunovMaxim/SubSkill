package com.subskill.dto;

import com.subskill.models.MicroSkill;

import java.util.Set;

public record SaveMicroSkillDto(
        Long id,
        Set<MicroSkill> microSkills) {
}
