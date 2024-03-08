package com.subskill.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.subskill.service.UserService;
import org.springframework.stereotype.Service;

import com.subskill.dto.MicroSkillDto;
import com.subskill.exception.MicroSkillAlreadySavedException;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.models.MicroSkill;
import com.subskill.models.SaveMicroskill;
import com.subskill.models.User;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.UserRepository;
import com.subskill.repository.SaveMicroskillRepository;
import com.subskill.service.SavedMicroskillService;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SaveMicroSkillServiceImplementation implements SavedMicroskillService {
    private final MicroSkillRepository microSkillRepository;
    private final UserRepository userRepository;
    private final SaveMicroskillRepository saveMicroskillRepository;
    private final UserService userService;

    @Override
    @Transactional
    public SaveMicroskill addMicroSkillToUser(long microskillId) {
        Long userId = userService.getAuthenticatedUser().getId();
        boolean alreadySaved = saveMicroskillRepository.existsByUserAndMicroSkills(
                userRepository.findById(userId).orElseThrow(NoUserInRepositoryException::new),
                microSkillRepository.findById(microskillId).orElseThrow(MicroSkillNotFoundException::new)
        );
        if (alreadySaved) {
            throw new MicroSkillAlreadySavedException("MicroSkill is already saved for the user");
        }
        MicroSkill microSkill = microSkillRepository.findById(microskillId)
                .orElseThrow(MicroSkillNotFoundException::new);
        User user = userRepository.findById(userId)
                .orElseThrow(NoUserInRepositoryException::new);
        SaveMicroskill saveMicroskill = new SaveMicroskill();
        saveMicroskill.getMicroSkills().add(microSkill);
        saveMicroskill.setUser(user);
        saveMicroskillRepository.save(saveMicroskill);
        return saveMicroskill;
    }

    @Override
    @Transactional
    public void deleteMicroSkillFromUser(long microskillId) {
        Long userId = userService.getAuthenticatedUser().getId();
        Optional<SaveMicroskill> saveMicroskill = saveMicroskillRepository.findByUserAndMicroSkills(
                userRepository.findById(userId).orElseThrow(NoUserInRepositoryException::new),
                microSkillRepository.findById(microskillId).orElseThrow(MicroSkillNotFoundException::new));
        saveMicroskill.ifPresent(microskill -> saveMicroskillRepository.deleteById(microskill.getId()));
    }

    @Override
    public Set<MicroSkillDto> allMicroSkillsOfUser(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(NoUserInRepositoryException::new);
        List<SaveMicroskill> saveMicroskills = saveMicroskillRepository.findByUser(user);
        return saveMicroskills.stream()
                .map(SaveMicroskill::getMicroSkills)
                .flatMap(Set::stream)
                .map(MicroSkill::build)
                .collect(Collectors.toSet());
    }

}
