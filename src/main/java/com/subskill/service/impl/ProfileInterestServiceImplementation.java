package com.subskill.service.impl;

import com.subskill.dto.InterestDto;
import com.subskill.enums.Tags;
import com.subskill.exception.InterestNotFoundException;
import com.subskill.models.Interest;
import com.subskill.models.MicroSkill;
import com.subskill.models.User;
import com.subskill.repository.MicroSkillRepository;
import com.subskill.repository.ProfileInterestRepository;
import com.subskill.service.ProfileInterestService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ProfileInterestServiceImplementation implements ProfileInterestService {
   private final ProfileInterestRepository profileInterestRepository;
   private final UserService userService;



    @Transactional
    @Override
    public InterestDto  showAllProfileInterest() {
        User user = userService.getAuthenticatedUser();
        Set<MicroSkill> microSkills = user.getCart().getListOfMicroSkills();

        List<Tags> allTags = microSkills.stream()
                .flatMap(microSkill -> microSkill.getTags().stream())
                .collect(Collectors.toList());
        Interest interest = new Interest();
        interest.setTags(allTags);

        return interest.build();
    }

    @Override
    public void  deleteProfileInterest(Long id) {
        Interest interest = profileInterestRepository.findById(id)
                .orElseThrow(InterestNotFoundException::new);
        profileInterestRepository.delete(interest);
        log.debug("Microskill with ID {} has been deleted", interest);

    }
}
