package com.subskill.controller;

import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.AuthDto.RegisteredUserDto;
import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.exception.RegistrationUserNotFoundException;
import com.subskill.service.AuthService;
import com.subskill.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    private final UserService userService;




    @Operation(summary = "Update our User")
    @PutMapping("/update/{email}")
    UserDto updateUser(@RequestBody  UserDto userDto) {
        log.debug("update user: received new information about user: {}", userDto);
        return userService.updateUser(userDto);
    }

    @Operation(summary = "Change password for User")
    @PutMapping("/password/{email}")
    UserDto changeUserPassword(@PathVariable String email,@RequestBody  UserDtoPassword userDtoPassword) {
        log.debug("The password has been changed {}", email);
        return userService.changePassword(email, userDtoPassword.password());
    }

    @Operation(summary = "Delete our User based on email")
    @DeleteMapping("delete/{email}")
    void deleteUser(@NotEmpty(message = MISSING_PERSON_EMAIL)
                    @Pattern(regexp = EMAIL_REGEXP, message = WRONG_EMAIL_FORMAT)@PathVariable String email) {
        log.debug("delete user: user with email {}", email);
        userService.deleteUser(email);
    }

    @Operation(summary = "List of Users")
    @GetMapping()
    List<UserDto> listOfUsers() {
        log.debug("List of users have been received");
        return userService.allUsers();
    }

}
