package com.subskill.service;

import com.subskill.dto.UserDto;

import java.util.List;

public interface UserService {
	UserDto registerUser(UserDto userDto);
	UserDto updateUser(UserDto userDto);

    UserDto changePassword(String email, String NewPassword);
	void deleteUser(String email);

    List<UserDto> allUsers();
}

