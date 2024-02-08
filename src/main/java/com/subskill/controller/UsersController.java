package com.subskill.controller;

import static com.subskill.api.ValidationConstants.EMAIL_REGEXP;
import static com.subskill.api.ValidationConstants.MISSING_PERSON_EMAIL;
import static com.subskill.api.ValidationConstants.WRONG_EMAIL_FORMAT;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
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
@CrossOrigin(maxAge = 3600, origins = "*")
public class UsersController {

    private final UserService usersService;

    @Operation(summary = "Register an new User")
    @PostMapping()
    UserDto registerUser(@RequestBody @Valid UserDto userDto) {
        log.debug("registerUser: received user data: {}", userDto);
        return usersService.registerUser(userDto);
    }

    @Operation(summary = "Update our User")
    @PutMapping("/update/{email}")
    UserDto updateUser(@RequestBody @Valid UserDto userDto) {
        log.debug("update user: received new information about user: {}", userDto);
        return usersService.updateUser(userDto);
    }

    @Operation(summary = "Change password for User")
    @PutMapping("/password/{email}")
    UserDto changeUserPassword(@RequestBody @Valid UserDto userDto) {
        log.debug("The password has been changed {}", userDto.email());
        return usersService.changePassword(userDto.email(), userDto.password());
    }

    @Operation(summary = "Delete our User based on email")
    @DeleteMapping("delete/{email}")
    void deleteUser(@NotEmpty(message = MISSING_PERSON_EMAIL)
                    @Pattern(regexp = EMAIL_REGEXP, message = WRONG_EMAIL_FORMAT) String email) {
        log.debug("delete user: user with email {}", email);
        usersService.deleteUser(email);
    }

    @Operation(summary = "List of Users")
    @GetMapping()
    List<UserDto> listOfUsers() {
        log.debug("List of users have been received");
        return usersService.allUsers();
    }

}
