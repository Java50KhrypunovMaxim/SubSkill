package com.subskill.service.impl;

import com.subskill.dto.InterestDto;
import com.subskill.enums.Tags;
import com.subskill.exception.InterestNotFoundException;
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

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProfileInterestServiceImplementation implements ProfileInterestService {
    private final ProfileInterestRepository profileInterestRepository;
    private final UserService userService;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    @Override
    public List<String> showAllProfileInterest() {
        List<String> interestNames = new ArrayList<>();
        List<Interest> profileInterests = profileInterestRepository.findAll();
        for (Interest interest : profileInterests) {
            interestNames.add(interest.getName());
        }
        return interestNames;
    }

    @Override
    public List<Interest> addInterestToUser(String tags) {
        List<Interest> userInterests = profileInterestRepository.findAll();
        User user = userService.getAuthenticatedUser();

//            List<InterestDto> newInterestDto  = new ArrayList<>();
//            for(Interest interest : userInterests) {
//                newInterestDto.add(interest.build());
//
//            }
        boolean interestExists = userInterests.stream().anyMatch(interest -> interest.getName().equals(tags));
        if (!interestExists) {
            Interest newInterest = new Interest(tags);
            newInterest = profileInterestRepository.save(newInterest);
            user.getInterests().add(newInterest);
            userRepository.save(user);
        }
        return userInterests;

    }

    @Override
    public void deleteProfileInterest(Long id) {
        Interest interest = profileInterestRepository.findById(id)
                .orElseThrow(InterestNotFoundException::new);
        profileInterestRepository.delete(interest);
        log.debug("Microskill with ID {} has been deleted", interest);

    }
}
