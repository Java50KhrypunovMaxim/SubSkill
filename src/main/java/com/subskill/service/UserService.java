package com.subskill.service;

import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.AuthDto.RegisteredUserDto;
import com.subskill.dto.UserDto;

import java.util.List;

public interface UserService {
//	UserDto registerUser(RegisteredUserDto userDto);
JwtResponse register(RegisteredUserDto registeredUserDto);
	UserDto updateUser(UserDto userDto);

    UserDto changePassword(String email, String NewPassword);
	void deleteUser(String email);

    List<UserDto> allUsers();





}

