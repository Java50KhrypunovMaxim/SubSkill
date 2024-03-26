package com.subskill.service;

import com.subskill.dto.InterestDto;

import java.util.List;

public interface ProfileInterestService {
    InterestDto showAllProfileInterest();

    void deleteProfileInterest(Long id);
}
