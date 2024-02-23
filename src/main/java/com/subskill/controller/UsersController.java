package com.subskill.controller;

import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.subskill.api.ValidationConstants.*;

@Validated
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(maxAge = 3600, origins = "*")
public class UsersController {

    private final UserService usersService;

//    @Operation(summary = "Register an new User")
//    @PostMapping()
//    UserDto registerUser(@RequestBody @Valid UserDto userDto) {
//        log.debug("registerUser: received user data: {}", userDto);
//        return usersService.registerUser(userDto);
//    }

    @Operation(summary = "Update our User")
    @PutMapping("/update/{email}")
    UserDto updateUser(@RequestBody  UserDto userDto) {
        log.debug("update user: received new information about user: {}", userDto);
        return usersService.updateUser(userDto);
    }

    @Operation(summary = "Change password for User")
    @PutMapping("/password/{email}")
    UserDto changeUserPassword(@PathVariable String email,@RequestBody  UserDtoPassword userDtoPassword) {
        log.debug("The password has been changed {}", email);
        return usersService.changePassword(email, userDtoPassword.password());
    }

    @Operation(summary = "Delete our User based on email")
    @DeleteMapping("delete/{email}")
    void deleteUser(@NotEmpty(message = MISSING_PERSON_EMAIL)
                    @Pattern(regexp = EMAIL_REGEXP, message = WRONG_EMAIL_FORMAT)@PathVariable String email) {
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
