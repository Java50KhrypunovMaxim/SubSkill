package com.subskill.models;


import com.subskill.dto.UserDto;

public record Mail(String title,
                   UserDto userDto,
                   String message) {

}
