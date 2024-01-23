package com.subskill.userService;

import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.exception.NotFoundException;
import com.subskill.exception.UserExistingEmailExeption;
import com.subskill.repository.UserRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.subskill.api.ValidationConstants;
import com.subskill.dto.UserDto;


import com.subskill.models.User;

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
		User newUser = new User();
		BeanUtils.copyProperties(userDto, newUser);
		User savedUser = userRepository.save(newUser);
		return convertToUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto) throws NotFoundException {

		if (userDto == null || userDto.email() == null) {
			throw new IllegalArgumentException(INVALID_INPUT_DATA);
		}
		User existingUser = userRepository.findByEmail(userDto.email())
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		BeanUtils.copyProperties(userDto, existingUser, "id", "email");
		User updatedUser = userRepository.save(existingUser);
		return convertToUserDto(updatedUser);
	}

	@Override
	public UserDto changePassword(UserDto userDto,String email ) throws NoUserInRepositoryException, NotFoundException {
		if (StringUtils.isBlank(email) || StringUtils.isBlank(userDto.password())) {
			throw new IllegalArgumentException(INVALID_INPUT_DATA);
		}

		Optional<User> optionalUser = userRepository.findByEmail(email);

		if (optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			existingUser.setPassword(userDto.password());

			User updatedUser;
			try {
				updatedUser = userRepository.save(existingUser);
			} catch (Exception e) {
				throw new NoUserInRepositoryException("Error changing password: " + e.getMessage());
			}

			return convertToUserDto(updatedUser);
		} else {
			throw new NotFoundException(USER_NOT_FOUND);
		}
	}

	@Override
	public UserDto deleteUser(String email) {
		userRepository.findByEmail(email).ifPresent(userRepository::delete);
		return null;
	}

	@Override
	public List<String> allUsers() {
		return userRepository.findAll().stream().map(User::getEmail).collect(Collectors.toList());
	}



	@Override
	public UserDto convertToUserDto(User user) {
		return new UserDto(
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				user.getNickname(),
				user.isOnline(),
				user.getImageUrl(),
				user.getRole()
		);
	}
}