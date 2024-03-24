package com.subskill.service.impl;

import com.subskill.api.ValidationConstants;
import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.exception.NotFoundException;
import com.subskill.exception.UnauthorizedAccessException;
import com.subskill.jwt.JwtTokenUtils;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService, ValidationConstants {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto, JwtResponse jwtResponse) throws NotFoundException {
        String usernameFromToken = jwtTokenUtils.getUsernameFromToken(jwtResponse.token());

        if (!userDto.username().equals(usernameFromToken)) {
            throw new UnauthorizedAccessException();
        }

        User user = getAuthenticatedUser();

        if (user == null || !user.getEmail().equals(userDto.email())) {
            throw new IllegalArgumentException(INVALID_INPUT_DATA);
        }

        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setOnline(userDto.online());
        user.setImageUrl(userDto.imageUrl());
        user.setRole(userDto.role());

        userRepository.save(user);
        log.debug("User with email {} has been updated", user.getEmail());
        return user.build();
    }

    @Override
    @Transactional
    public UserDto changePassword(UserDtoPassword newPassword) {
        User user = getAuthenticatedUser();
        if (user == null) {
            throw new IllegalArgumentException(INVALID_INPUT_DATA);
        }

        user.setPassword(passwordEncoder.encode(newPassword.password()));
        userRepository.save(user);
        log.debug("Password for user with email {} has been changed", user.getEmail());
        return user.build();
    }

    @Override
    @Transactional
    public void deleteUser() {
        User authenticatedUser = getAuthenticatedUser();
        userRepository.getReferenceById(authenticatedUser.getId());
        log.debug("user with email {} has been deleted", authenticatedUser.getEmail());
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> allUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> listOfUserDto = users.stream()
                .map(User::build)
                .toList();
        log.debug("Showing all users that already registered on site");
        return listOfUserDto;

    }

    @Override
    public String nameUserByToken() {
        return getAuthenticatedUser().getUsername();
    }

    @Override
    public User getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(NoUserInRepositoryException::new);
    }
}