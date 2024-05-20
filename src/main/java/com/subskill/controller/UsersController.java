package com.subskill.controller;

import com.subskill.dto.UserDto;
import com.subskill.dto.UserDtoPassword;
import com.subskill.exception.UserNotFoundException;
import com.subskill.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void changeUserPassword( @RequestBody UserDtoPassword newPassword,@RequestParam String email) {
        log.debug("Changing password for user ");
        userService.changePassword(newPassword,email);
    }
    @Operation(summary = "Delete our User based on email")
    @DeleteMapping("/delete")
    void deleteUser() {
        userService.deleteUser();
    }

    @Operation(summary = "List of Users")
    @GetMapping("/all")
    List<UserDto> listOfUsers() {
        log.debug("List of users have been received ");
        return userService.allUsers();
    }
    @Operation(summary = "Forgot password?")
    @GetMapping("/password-recovery")
    public String passwordLost(@RequestParam String email) {
        log.debug("guess who lost a password? Yes, you! email : " + email);
            return userService.passwordRecovery(email);
    }


    @Operation(summary = "Mail password recovery")
    @PostMapping("/password-email")
    public ResponseEntity<String> passwordRecovery(@RequestParam String mail) {
        try {
            String result = userService.passwordRecoveryByEmail(mail);
            return ResponseEntity.ok(result);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(AN_ERROR_OCCURRED);
        }
    }
    @Operation(summary = "new password with token from mail")
    @PutMapping("/mailReset")
    public ResponseEntity<String> passwordReset(@RequestParam String token, @RequestParam String mail, @RequestParam String password) {

        try {
            userService.resetPasswordWithToken(mail, token, password);
            return ResponseEntity.ok(PASSWORD_RESET_SC);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(USER_NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(FAILED_RESET_PASSWORD);
        }
    }


}