package com.subskill.service;

import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.AuthDto.LoginDto;

public interface AuthService {
    JwtResponse login(LoginDto request);


//    JwtResponse register(RegisteredUserDto registeredUserDto);



}
