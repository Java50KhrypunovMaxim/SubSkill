package com.subskill.service.impl;

import com.subskill.dto.InterestDto;
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
import org.springframework.web.bind.annotation.RequestBody;

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
    public List<InterestDto> addInterestToUser(String tags) {
        List<Interest> userInterests = profileInterestRepository.findByNameContaining(tags);
        User user = userService.getAuthenticatedUser();

        boolean interestExists = userInterests.stream().anyMatch(interest1 -> interest1.getName().equals(tags));
        if (!interestExists) {
            Interest interest =  new Interest(tags);
            interest.getUserInterest().add(user);
            user.getInterests().add(interest);
            profileInterestRepository.save(interest);
            userRepository.save(user);
        }
        List<InterestDto> interestDto = new ArrayList<>();
        for(Interest interest : userInterests) {
            interestDto.add(interest.build());
        }
        return interestDto;

    }

    @Override
    public void deleteProfileInterest(Long id) {
        Interest interest = profileInterestRepository.findById(id)
                .orElseThrow(InterestNotFoundException::new);
        profileInterestRepository.delete(interest);
        log.debug("Microskill with ID {} has been deleted", interest);

    }
}
