package com.subskill.controller;

import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.AuthDto.LoginDto;
import com.subskill.dto.AuthDto.RegisteredUserDto;
import com.subskill.exception.IllegalUsersStateException;
import com.subskill.exception.RegistrationUserNotFoundException;
import com.subskill.jwt.JwtTokenUtils;
import com.subskill.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenUtils jwtTokenUtils;

    @Operation(description = "authenticate our user")
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> createAuthToken(@RequestBody RegisteredUserDto registeredUserDto) {
        try {
            JwtResponse response = authService.register(registeredUserDto);
            log.debug("User {} has been successfully registered", registeredUserDto.username());
            return ResponseEntity.ok(response);
        } catch (IllegalUsersStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(description = "login for user")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto) {
        try {
            JwtResponse response = authService.login(loginDto);
            String username = jwtTokenUtils.getUsernameFromToken(response.token());
            log.info("User '{}' logged in successfully with token {}.", username, response.token());
            return ResponseEntity.ok(response);
        } catch (RegistrationUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
