package com.subskill.service;

import com.subskill.api.ValidationConstants;
import com.subskill.dto.UserDto;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.exception.NotFoundException;
import com.subskill.exception.UserExistingEmailExeption;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService, ValidationConstants {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto registerUser(UserDto userDto) throws UserExistingEmailExeption {
	 Optional<User> existingUserOptional = userRepository.findByEmail(userDto.email());
    if (existingUserOptional.isPresent()) {
        throw new UserExistingEmailExeption("User with email " + userDto.email() + " already exists");    }
		User newUser = User.of(userDto);
		User savedUser = userRepository.save(newUser);
		return  savedUser.build(newUser) ;
	}

	@Override
	public UserDto updateUser(UserDto userDto) throws NotFoundException {
		if (userDto == null || userDto.email() == null) {
			throw new IllegalArgumentException(INVALID_INPUT_DATA);
		}
		User existingUser = userRepository.findByEmail(userDto.email())
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		User updatedUser = userRepository.save(existingUser);
		return updatedUser.build(existingUser);
	}

	@Override
	public UserDto changePassword(UserDto userDto, String email) throws NoUserInRepositoryException, NotFoundException {
		User existingUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		existingUser.setPassword(userDto.password());
		try {
			userRepository.save(existingUser);
			return userDto;
		} catch (Exception e) {
			throw new NoUserInRepositoryException("Error changing password: " + e.getMessage());
		}
	}

	@Override
	public void deleteUser(String email) {
		userRepository.findByEmail(email).ifPresent(userRepository::delete);
	}

	@Override
	public List<String> allUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(User::getEmail).collect(Collectors.toList());
	}




}