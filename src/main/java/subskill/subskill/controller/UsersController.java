package subskill.subskill.controller;

import static subskill.subskill.api.ValidationConstants.*;
import static subskill.subskill.api.ValidationConstants.MISSING_PERSON_EMAIL;
import static subskill.subskill.api.ValidationConstants.WRONG_EMAIL_FORMAT;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import subskill.subskill.dto.AdminDto;
import subskill.subskill.dto.UserDto;
import subskill.subskill.service.UserService;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j 

public class UsersController {
	
	final UserService usersService;
	
	@PostMapping()
	UserDto registerUser(@RequestBody @Valid UserDto userDto) {
		log.debug("registerUser: received user data: {}", userDto);
		return usersService.registerUser(userDto);
	}
	
	@PutMapping("update/{email}")
	UserDto updateUser(@RequestBody @Valid UserDto userDto) {
		log.debug("update user: received new information about user: {}", userDto);
		return usersService.updateUser(userDto);
	}
	
	@PutMapping("change-password/{userDto}/{newPassword}")
	UserDto changeUserPassword (@NotEmpty (message=MISSING_PASSWORD_MESSAGE)
	@Pattern(regexp = PASSWORD_REGEXP, message=WRONG_PASSWORD_CREATION_MESSAGE) UserDto userDto, String newPassword) {
		log.debug("The password has been changed {}", newPassword);
		return usersService.changePassword(userDto,newPassword);
	}
	
	@DeleteMapping("/{email}")
	UserDto deleteUser(@NotEmpty (message=MISSING_PERSON_EMAIL)
	@Pattern(regexp = EMAIL_REGEXP, message=WRONG_EMAIL_FORMAT) String email) {
		log.debug("delete user: user with email {}", email);
		return usersService.deletePerson(email);
	}
	
	@GetMapping ("/all")
	List<String> listOfUsers() {
        log.debug("List of users have been received");
        return usersService.allUsers();
    }
	
}
