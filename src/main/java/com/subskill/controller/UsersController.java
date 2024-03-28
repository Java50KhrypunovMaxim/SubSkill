package com.subskill.controller;

import com.subskill.dto.AuthDto.JwtResponse;
import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
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
    @PutMapping("/update")
    UserDto updateUser(@RequestBody UserDto userDto) {
        log.debug("update user: received new information about user: {}", userDto);
        return userService.updateUser(userDto);
    }
    @GetMapping("/name")
    public String getNameUserByToken() {
        return userService.nameUserByToken();
    }

    @Operation(summary = "Change password for User")
    @PutMapping("/password")
    @Transactional
    public void changeUserPassword( @RequestBody UserDtoPassword newPassword) {
        log.debug("Changing password for user ");
        userService.changePassword(newPassword);
    }
    @Operation(summary = "Delete our User based on email")
    @DeleteMapping("/delete")
    void deleteUser() {
        userService.deleteUser();
    }

    @Operation(summary = "List of Users")
    @GetMapping("/all")
    List<UserDto> listOfUsers() {
        log.debug("List of users have been received "  );
        return userService.allUsers();
    }

}