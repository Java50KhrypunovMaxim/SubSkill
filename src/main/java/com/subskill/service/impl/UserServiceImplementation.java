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
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    @CachePut(value = "users", key = "#userDto.email()", cacheManager = "objectCacheManager")
    public UserDto updateUser(UserDto userDto) throws NotFoundException {
        if (userDto == null || userDto.email() == null) {
            throw new IllegalArgumentException(INVALID_INPUT_DATA);
        }
        User existingUser = userRepository.findByEmail(userDto.email())
                .orElseThrow(UserExistingEmailExeption::new);
        if (userDto.password() != null && !userDto.password().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userDto.password()));
        }
        userRepository.save(existingUser);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        UserDto updatedUser = modelMapper.map(existingUser, UserDto.class);
        log.debug("user with email {} has been updated", existingUser.getEmail());
        return updatedUser;
    }

    @Override
    @Transactional
    public UserDto changePassword(String email, String NewPassword) {
        User optionalExistingUser = userRepository.findByEmail(email)
               .orElseThrow(UserExistingEmailExeption::new);

        optionalExistingUser.setPassword(NewPassword);
        userRepository.save(optionalExistingUser);
        UserDto userWithNewPassword = modelMapper.map(optionalExistingUser, UserDto.class);
        log.debug("Password in email {} has been changed", optionalExistingUser.getEmail());
        return userWithNewPassword;
    }

    @Override
    @Transactional
    @CacheEvict(value = "user", key = "#email", cacheManager = "objectCacheManager")
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NoUserInRepositoryException::new);
        userRepository.deleteById(user.getId());
        log.debug("user with email {} has been deleted", user.getEmail());
    }

    @Transactional(readOnly = true)
    @Override
    @Cacheable(value = "users")
    public List<UserDto> allUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDto = users.stream()
                .map(User::build)
                .toList();
        log.debug("Showing all users that already registered on site");
        return userDto;

    }

    @Override
    public User getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }
}