package com.subskill.controller;

import static com.subskill.api.ValidationConstants.EMAIL_REGEXP;
import static com.subskill.api.ValidationConstants.MISSING_PERSON_EMAIL;
import static com.subskill.api.ValidationConstants.WRONG_EMAIL_FORMAT;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.subskill.dto.UserDto;
import com.subskill.service.UserService;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j 
public class UsersController {

	private final UserService usersService;
	
	@PostMapping()
	UserDto registerUser(@RequestBody @Valid UserDto userDto) {
		log.debug("registerUser: received user data: {}", userDto);
		return usersService.registerUser(userDto);
	}
	
	@PutMapping("/update/{email}")
	UserDto updateUser(@RequestBody @Valid UserDto userDto) {
		log.debug("update user: received new information about user: {}", userDto);
		return usersService.updateUser(userDto);
	}

	@PutMapping("/password/{email}")
	UserDto changeUserPassword(@RequestBody @Valid UserDto userDto) {
		log.debug("The password has been changed {}", userDto.email());
		return usersService.changePassword(userDto.email(), userDto.password());
	}

	@DeleteMapping("delete/{email}")
	void deleteUser(@NotEmpty(message = MISSING_PERSON_EMAIL)
					@Pattern(regexp = EMAIL_REGEXP, message = WRONG_EMAIL_FORMAT) String email) {
		log.debug("delete user: user with email {}", email);
		usersService.deleteUser(email);
	}

	@GetMapping()
	List<String> listOfUsers() {
        log.debug("List of users have been received");
        return usersService.allUsers();
    }

}
