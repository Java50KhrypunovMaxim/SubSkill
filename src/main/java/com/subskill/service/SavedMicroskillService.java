package com.subskill.service;

import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.SaveMicroSkillDto;

import java.util.Set;

public interface SavedMicroskillService {
    SaveMicroSkillDto addMicroSkillToUser(long microskillId);

    void deleteMicroSkillFromUser(long userId);

    Set<MicroSkillDto> allMicroSkillsOfUser();
}
