package com.subskill.service;

import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.models.User;

import java.util.List;

public interface UserService {
    UserDto updateUser(UserDto userDto);
    String passwordRecovery(String email);
    void changePassword(UserDtoPassword newPassword,String email);

    void deleteUser();

    List<UserDto> allUsers();

    String nameUserByToken();

    User getAuthenticatedUser();
}

