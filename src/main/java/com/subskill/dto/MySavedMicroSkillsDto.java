package com.subskill.dto;

import com.subskill.models.MicroSkill;
import com.subskill.models.User;

import java.util.List;

public record MySavedMicroSkillsDto(
         Long id,
    List<MicroSkill> microSkill,
         User user
) {
}
