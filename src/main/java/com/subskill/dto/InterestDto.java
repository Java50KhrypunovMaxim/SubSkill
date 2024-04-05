package com.subskill.dto;


import com.subskill.enums.Tags;
import com.subskill.models.User;

import java.util.List;

public record InterestDto(
        Long id,
         Tags name
){
}
