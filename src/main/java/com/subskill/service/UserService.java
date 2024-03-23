package com.subskill.service;

import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.models.User;

import java.util.List;

public interface UserService {
    UserDto updateUser(UserDto userDto);
    UserDto changePassword(UserDtoPassword newPassword);
	void deleteUser(String email);
    List<UserDto> allUsers();
    String nameUserByToken();
    User getAuthenticatedUser();
}

