package com.subskill.service.impl;

import com.subskill.dto.MicroSkillDto;
import com.subskill.dto.SaveMicroSkillDto;
import com.subskill.exception.MicroSkillNotFoundException;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.models.MicroSkill;
import com.subskill.models.SaveMicroskill;
import com.subskill.models.User;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.SaveMicroskillRepository;
import com.subskill.repository.UserRepository;
import com.subskill.service.SavedMicroskillService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SaveMicroSkillServiceImplementation implements SavedMicroskillService {

    private final MicroSkillRepository microSkillRepository;
    private final UserRepository userRepository;
    private final SaveMicroskillRepository saveMicroskillRepository;
    private final UserService userService;

    @Override
    @Transactional
    public SaveMicroSkillDto addMicroSkillToUser(long microskillId) {
      User user = userService.getAuthenticatedUser();
//        boolean alreadySaved = saveMicroskillRepository.existsByUserAndMicroSkills(
//                userRepository.findById(user.getId()).orElseThrow(NoUserInRepositoryException::new),
//                microSkillRepository.findById(microskillId).orElseThrow(MicroSkillNotFoundException::new)
//        );
//        if (alreadySaved) {
//            throw new MicroSkillAlreadySavedException();
//        }
        MicroSkill microSkill = microSkillRepository.findById(microskillId)
                .orElseThrow(MicroSkillNotFoundException::new);
        SaveMicroskill saveMicroskill = new SaveMicroskill();
        saveMicroskill.setUser(user);
        saveMicroskill.setMicroSkills(new HashSet<>());
        saveMicroskill.getMicroSkills().add(microSkill);
        saveMicroskillRepository.save(saveMicroskill);
        return new SaveMicroSkillDto(
                saveMicroskill.getId(),
                saveMicroskill.getMicroSkills()
        );
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
    public Set<MicroSkillDto> allMicroSkillsOfUser( ) {
        User user = userService.getAuthenticatedUser();
        List<SaveMicroskill> saveMicroskills = saveMicroskillRepository.findByUser(user);
        return saveMicroskills.stream()
                .map(SaveMicroskill::getMicroSkills)
                .flatMap(Set::stream)
                .map(MicroSkill::build)
                .collect(Collectors.toSet());
    }

}
