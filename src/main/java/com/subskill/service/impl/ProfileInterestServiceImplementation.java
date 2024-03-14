package com.subskill.service.impl;

import com.subskill.dto.InterestDto;
import com.subskill.exception.InterestNotFoundException;
import com.subskill.models.Interest;
import com.subskill.repository.ProfileInterestRepository;
import com.subskill.service.ProfileInterestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProfileInterestServiceImplementation implements ProfileInterestService {
    ProfileInterestRepository profileInterestRepository;

    @Transactional
    @Override
    public List<InterestDto> showAllProfileInterest() {
        List<Interest> listOfInterest = profileInterestRepository.findAll();
        log.debug("Our list if interest : {}", listOfInterest);
        return listOfInterest.stream()
                .map(Interest::build)
                .toList();
    }

    @Override
    public void  deleteProfileInterest(Long id) {
        Interest interest = profileInterestRepository.findById(id)
                .orElseThrow(InterestNotFoundException::new);
        profileInterestRepository.delete(interest);
        log.debug("Microskill with ID {} has been deleted", interest);

    }
}
