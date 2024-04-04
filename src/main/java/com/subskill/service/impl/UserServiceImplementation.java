package com.subskill.service.impl;

import com.subskill.api.ValidationConstants;
import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.exception.UserNotFoundException;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.security.SecureRandom;
import java.util.Base64;
@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService, ValidationConstants {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {


        User user = getAuthenticatedUser();
        if (user == null || userDto == null || user.getEmail() == null) {
            throw new IllegalArgumentException(INVALID_INPUT_DATA);
        }
        user.setUsername(userDto.username());
        user.setImageUrl(userDto.imageUrl());

        userRepository.save(user);
        log.debug("User with email {} has been updated", user.getEmail());
        return user.build();
    }

    @Override
    public String passwordRecovery(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String randomPassword = generateRandomPassword();

            user.setPassword(passwordEncoder.encode(randomPassword));
            userRepository.save(user);

            return randomPassword;
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    @Transactional
    public void changePassword(UserDtoPassword newPassword,String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setPassword(passwordEncoder.encode(newPassword.password()));
        userRepository.save(user);
        log.debug("Password for user with email {} has been changed", user.getEmail());

    }

    @Override
    @Transactional
    public void deleteUser() {
        User authenticatedUser = getAuthenticatedUser();
        userRepository.deleteById(authenticatedUser.getId());
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
    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}