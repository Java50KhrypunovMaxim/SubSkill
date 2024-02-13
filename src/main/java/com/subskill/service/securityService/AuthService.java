package com.subskill.service.securityService;

import com.subskill.dto.AuthDto.JwtRequest;
import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.AuthDto.LoginDto;
import com.subskill.dto.AuthDto.RegisteredUserDto;
import com.subskill.enums.Roles;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public JwtResponse login(LoginDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );
        var user = userRepository.findByEmail(request.email()).orElseThrow();
        var jwt = jwtTokenUtils.generateToken(user);
        return JwtResponse.builder()
                .token(jwt)
                .build();
    }

    public JwtResponse register(RegisteredUserDto registeredUserDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registeredUserDto.username(), registeredUserDto.password()));
        UserDetails userDetails = userRepository.loadUserByUsername(registeredUserDto.username());
        String token = jwtTokenUtils.generateToken(userDetails);
        var user = User.builder()
                .username(registeredUserDto.username())
                .email(registeredUserDto.email())
                .password(passwordEncoder.encode(registeredUserDto.password()))
                .role(Roles.USER)
                .build();
        userRepository.save(user);
        return JwtResponse.builder()
                .token(token)
                .build();
    }
    public User authenticateUserFromToken(String token) {
        String username = jwtTokenUtils.getUsername(token);
        return userRepository.findByUsername(username).orElseThrow();
    }
}