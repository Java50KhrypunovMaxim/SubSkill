package com.subskill.service.impl;

import com.subskill.api.ValidationConstants;
import com.subskill.dto.UserDto;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.exception.NotFoundException;
import com.subskill.exception.UserExistingEmailExeption;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;
import com.subskill.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService, ValidationConstants {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDto registerUser(UserDto userDto) {
        Optional<User> existingUserOptional = userRepository.findByEmail(userDto.email());
        if (existingUserOptional.isPresent()) {
            throw new UserExistingEmailExeption("User with email " + userDto.email() + " already exists");
        }
        User newUser = User.of(userDto);
        userRepository.save(newUser);
        log.debug("user with email {} has been registered", newUser.getEmail());
        return newUser.build();
    }

    @Override
    @Transactional
    public UserDto updateUser(UserDto userDto) throws NotFoundException {
        if (userDto == null || userDto.email() == null) {
            throw new IllegalArgumentException(INVALID_INPUT_DATA);
        }
        User existingUser = userRepository.findByEmail(userDto.email())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        deleteUser(existingUser.getEmail());
        UserDto updatedUser = registerUser(userDto);
        log.debug("user with email {} has been updated", updatedUser.email());
        return updatedUser;
    }

    @Override
    @Transactional
    public UserDto changePassword(String email, String NewPassword) {
        User optionalExistingUser = userRepository.findByEmail(email).orElseThrow();
        optionalExistingUser.setPassword(NewPassword);
        userRepository.save(optionalExistingUser);
        log.debug("Password in email {} has been changed", optionalExistingUser.getEmail());
        return optionalExistingUser.build();
    }

    @Override
    @Transactional
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NoUserInRepositoryException::new);
        userRepository.deleteById(user.getId());
        log.debug("user with email {} has been deleted", user.getEmail());
    }
    @Transactional(readOnly = true)
    @Override
    public List<UserDto> allUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDto = users.stream()
                .map(User::build)
                .toList();
        log.debug("Showing all users that alreagy registred of site");
        return userDto;

    }




}