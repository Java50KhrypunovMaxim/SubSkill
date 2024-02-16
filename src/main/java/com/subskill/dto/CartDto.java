package com.subskill.dto;


import com.subskill.models.User;

import java.util.List;

public record CartDto(
        User user,
        List<MicroSkillDto> listOfMicroSkills
) {}