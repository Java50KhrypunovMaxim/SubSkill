package com.subskill.service;

import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.models.User;

import java.util.List;

public interface UserService {
    UserDto updateUser(UserDto userDto);

    String passwordRecovery(String email);

    void changePassword(UserDtoPassword newPassword, String email);

    void deleteUser();

    List<UserDto> allUsers();

   String passwordRecoveryByEmail(String email);

   void resetPasswordWithToken(String mail,String token,String password);

    String nameUserByToken();

    User getAuthenticatedUser();
}

