package com.subskill.service;

import com.subskill.dto.InterestDto;

import java.util.List;

public interface ProfileInterestService {
    List<InterestDto> showAllProfileInterest();

    void deleteProfileInterest(Long id);
}
