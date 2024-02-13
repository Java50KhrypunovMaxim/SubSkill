package com.subskill.controller;

import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.AuthDto.LoginDto;
import com.subskill.dto.AuthDto.RegisteredUserDto;
import com.subskill.exception.RegistrationUserNotFoundException;
import com.subskill.models.User;
import com.subskill.service.securityService.AuthService;
import com.subskill.service.securityService.JwtTokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.subskill.api.ValidationConstants.USER_NOT_FOUND;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final JwtTokenUtils jwtTokenUtils;


    @Operation(description = "authenticate our user")
    @PostMapping("/register")
    public ResponseEntity<?> createAuthToken(@RequestBody RegisteredUserDto registeredUserDto) {
        try {
            JwtResponse response = authService.register(registeredUserDto);
            return ResponseEntity.ok(response);
        } catch (RegistrationUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        }
    }

    @Operation(description = "login for user")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody LoginDto loginDto) {
        try {
            JwtResponse response = authService.login(loginDto);
            String username = jwtTokenUtils.getUsername(response.getToken());
            List<String> roles = jwtTokenUtils.getRoles(response.getToken());
            log.info("User '{}' with roles '{}' logged in successfully.", username, roles);
            return ResponseEntity.ok(response);
        } catch (RegistrationUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(description = "authenticate user from token")
    @GetMapping("/authenticateUser")
    public ResponseEntity<User> authenticateUser(@RequestParam String token) {
        User authenticatedUser = authService.authenticateUserFromToken(token);
        return ResponseEntity.ok(authenticatedUser);
    }
}
