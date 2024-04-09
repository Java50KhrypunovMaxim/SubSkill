package com.subskill.service.impl;

import com.subskill.dto.InterestDto;
import com.subskill.enums.Tags;
import com.subskill.models.Interest;
import com.subskill.models.User;
import com.subskill.repository.ProfileInterestRepository;
import com.subskill.repository.UserRepository;
import com.subskill.service.ProfileInterestService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@AllArgsConstructor
public class ProfileInterestServiceImplementation implements ProfileInterestService {
    private final ProfileInterestRepository profileInterestRepository;
    private final UserService userService;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public List<InterestDto> showAllProfileInterest() {
        List<Interest> interests = profileInterestRepository.findAll();
        return interests
                .stream()
                .map(Interest::build)
                .toList();
    }

    @Override
    public void setInterests(List<Tags> interests) {
        User user = userService.getAuthenticatedUser();
        List<Interest> allInterests = profileInterestRepository.findAll();
        List<Interest> userInterests = user.getInterests();
        List<Tags> existingTags = userInterests.stream()
                .map(Interest::getName)
                .toList();
        for (Tags tag : interests) {
            if (existingTags.contains(tag)) {
                Interest interest = findInterestByName(allInterests, tag);
                if (interest != null) {
                    userInterests.add(interest);
                    profileInterestRepository.save(interest);
                } else {
                    log.error("Interest with name {} not found", tag);
                }
            } else {
                Interest newInterest = new Interest();
                newInterest.setName(tag);
                newInterest.getUserInterest().add(user);
                userInterests.add(newInterest);
                profileInterestRepository.save(newInterest);
            }
        }
        userRepository.save(user);
    }


    private Interest findInterestByName(List<Interest> interests, Tags tagName) {
        for (Interest interest : interests) {
            if (interest.getName().equals(tagName)) {
                return interest;
            }
        }
        return null;
    }

    @Transactional
    @Override
    public void deleteProfileInterest(Tags tags) {
        List<Interest> interests = profileInterestRepository.findAll();
        boolean hasInterestWithTag = interests.stream().anyMatch(tag -> tag.getName().equals(tags));
        if (hasInterestWithTag) {
            profileInterestRepository.deleteByName(tags);
        }

    }
}
