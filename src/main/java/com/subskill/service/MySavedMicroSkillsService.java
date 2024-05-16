package com.subskill.service;

import com.subskill.dto.MicroSkillDto;

import java.util.Set;

public interface MySavedMicroSkillsService {
    void addMicroSkill(long microskillId);

    Set<MicroSkillDto> allSavedMicroSkillsOfUser();
}
