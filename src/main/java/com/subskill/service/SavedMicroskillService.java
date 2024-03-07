package com.subskill.service;

import java.util.Set;

import com.subskill.dto.MicroSkillDto;
import com.subskill.models.MicroSkill;
import com.subskill.models.SaveMicroskill;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SavedMicroskillService {
    SaveMicroskill addMicroSkillToUser(long microskillId);

    void deleteMicroSkillFromUser(long userId);

    Set<MicroSkillDto> allMicroSkillsOfUser(long userId);
}
