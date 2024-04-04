package com.subskill.service;

import com.subskill.dto.InterestDto;
import com.subskill.enums.Tags;
import com.subskill.models.Interest;
import com.subskill.models.User;

import java.util.List;

public interface ProfileInterestService {
    List<String> showAllProfileInterest();

    List<InterestDto> addInterestToUser(String tags);

    void deleteProfileInterest(Long id);
}
