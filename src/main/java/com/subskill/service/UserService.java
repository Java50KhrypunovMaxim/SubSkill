package com.subskill.service;

import java.util.List;

import com.subskill.dto.UserDto;
import com.subskill.models.User;

public interface UserService {
	UserDto registerUser(UserDto userDto);
	UserDto updateUser(UserDto userDto);
	UserDto changePassword (UserDto userDto, String newPassword);
	UserDto deleteUser(String email);
	List<String> allUsers();
	List<String> allAdmins();
	UserDto convertToUserDto(User user);
}

