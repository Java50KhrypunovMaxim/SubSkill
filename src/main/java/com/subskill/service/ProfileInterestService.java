package com.subskill.service;

import com.subskill.dto.InterestDto;
import com.subskill.enums.Tags;

import java.util.List;

public interface ProfileInterestService {
    List<InterestDto>  showAllProfileInterest();

     void setInterests( List<Tags> interests);
     void deleteProfileInterest( Tags tags);
}
