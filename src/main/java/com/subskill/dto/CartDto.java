package com.subskill.dto;


import com.subskill.models.User;

import java.util.List;

public record CartDto(
        UserDto userDto,
        List<MicroSkillDto> listOfMicroSkills
) {}