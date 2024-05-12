package com.subskill.service.impl;

import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.models.MicroSkill;
import com.subskill.models.MySavedMicroSkills;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.MySavedMicroSkillsRepository;
import com.subskill.service.MySavedMicroSkillsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MySavedMicroSkillsServiceImpl implements MySavedMicroSkillsService {
    private final MySavedMicroSkillsRepository mySavedMicroSkillsRepository;
    private final MicroSkillRepository microSkillRepository;

    @Override
    public void addMicroSkill(long microskillId) {
        MicroSkill microSkill = microSkillRepository.findById(microskillId).orElseThrow(MicroSkillNotFoundException::new);
        MySavedMicroSkills mySavedMicroSkills = new MySavedMicroSkills();
        mySavedMicroSkills.setMicroSkills(Set.of(microSkill));
        mySavedMicroSkillsRepository.save(mySavedMicroSkills);
    }

    @Override
    public List<MySavedMicroSkills> getAllSavedMicroSkills() {
        List<MySavedMicroSkills> allMicroSkill = mySavedMicroSkillsRepository.findAll();
        return allMicroSkill.stream()
                .toList();
    }
}
