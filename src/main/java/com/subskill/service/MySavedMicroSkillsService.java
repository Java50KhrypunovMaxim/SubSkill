package com.subskill.service;

import com.subskill.models.MySavedMicroSkills;

import java.util.List;

public interface MySavedMicroSkillsService {
    void addMicroSkill(long microskillId);

    List<MySavedMicroSkills> getAllSavedMicroSkills();
}
