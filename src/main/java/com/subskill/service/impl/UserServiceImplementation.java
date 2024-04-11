package com.subskill.service.impl;

import com.subskill.api.ValidationConstants;
import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.exception.UserNotFoundException;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;
import com.subskill.service.SendMailService;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.security.SecureRandom;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService, ValidationConstants  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SendMailService sendMailService;

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) {


        User user = getAuthenticatedUser();
        if (user == null || userDto == null || user.getEmail() == null) {
            throw new IllegalArgumentException(INVALID_INPUT_DATA);
        }
        user.setEmail(userDto.email());
        user.setJobTitle(userDto.jobTitle());
        user.setUsername(userDto.username());

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
    public String passwordRecoveryByEmail(String email) {
        String token = generateRandomPassword();

        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        user.setToken(token);
        user.setTokenCreationDate(LocalDateTime.now()); 
        userRepository.save(user);
        sendMailService.sendToken(email,token);
        return "Email was sent";
    }

    @Override
    public void resetPasswordWithToken(String mail, String token, String password) {
        User user = userRepository.findByEmail(mail).orElseThrow(UserNotFoundException::new);
        if (isTokenValid(user, token)) {
            String hashedPassword = passwordEncoder.encode(password);
            user.setPassword(hashedPassword);
            user.setToken(null);
            userRepository.save(user);
        } else {
            throw new UserNotFoundException();
        }
    }
    private boolean isTokenValid(User user, String token) {
        LocalDateTime tokenCreationDate = user.getTokenCreationDate();
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(tokenCreationDate, now);
        long hoursPassed = duration.toHours();
        return user.getToken() != null && user.getToken().equals(token) && hoursPassed <= 24;
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