package com.subskill.service.impl;

import com.subskill.dto.MicroSkillDto;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.models.MicroSkill;
import com.subskill.models.MySavedMicroSkills;
import com.subskill.models.User;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.MySavedMicroSkillsRepository;
import com.subskill.service.MySavedMicroSkillsService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MySavedMicroSkillsServiceImpl implements MySavedMicroSkillsService {
    private final MySavedMicroSkillsRepository mySavedMicroSkillsRepository;
    private final MicroSkillRepository microSkillRepository;
    private final UserService userService;

    @Override
    public void addMicroSkill(long microskillId) {
        MicroSkill microSkill = microSkillRepository.findById(microskillId).orElseThrow(MicroSkillNotFoundException::new);
        MySavedMicroSkills mySavedMicroSkills = new MySavedMicroSkills();
        mySavedMicroSkills.setMicroSkills(Set.of(microSkill));
        mySavedMicroSkillsRepository.save(mySavedMicroSkills);
    }
    public Set<MicroSkillDto> allSavedMicroSkillsOfUser() {
        User user = userService.getAuthenticatedUser();
        List<MySavedMicroSkills> saveMicroskills = mySavedMicroSkillsRepository.findByUser(user);
        return saveMicroskills.stream()
                .map(MySavedMicroSkills::getMicroSkills)
                .flatMap(Set::stream)
                .map(MicroSkill::build)
                .collect(Collectors.toSet());
    }
}
