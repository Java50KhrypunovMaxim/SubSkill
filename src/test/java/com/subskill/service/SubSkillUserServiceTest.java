package com.subskill.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.subskill.dto.ArticleDto;
import com.subskill.enums.Status;
import com.subskill.exception.ArticleNotFoundException;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.exception.RegistrationUserNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import com.subskill.dto.UserDto;
import com.subskill.enums.Roles;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = {"classpath:data_for_the_database.sql"})
class SubSkillUserServiceTest {
	private static final String USER_SERVICE_TEST = "User Service Test: ";

	private static final String USERNAME1 = "test_user";
	private static final String USERNAME2 = "admin_user";


	private static final String EMAIL1 = "user1@example.com";
	private static final String EMAIL2 = "name1@tel-ran.co.il";



	private static final String PASSWORD1 = "$2a$12$X7yEccnTctNxzrsLI46Wn.U6zT7YZN9QaNheqpVHuYC9JXo2uL14a";
	private static final String PASSWORD2 = "tel567";

	private static final String IMAGEURL1 = "https://example.com/image1.jpg";
	private static final String IMAGEURL2 = "https://example.com/image2.jpg";

    UserDto userDto1 = new UserDto(USERNAME1, EMAIL1, PASSWORD1, Status.ONLINE, IMAGEURL1, Roles.USER);
    UserDto userDto2 = new UserDto(USERNAME2, EMAIL2, PASSWORD2, Status.ONLINE, IMAGEURL2, Roles.USER);

	public static final List<String> ALL_USERS_NICKNAMES = Arrays.asList(USERNAME1, USERNAME2
		);
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserRepository userRepo;

	@Autowired
	UserService userService;;

	@Test
	@DisplayName(USER_SERVICE_TEST + "UpdateUser")
	void testUpdateUser() {
		String userEmail = "user1@example.com";

		UserDto updatedUserDto = userService.updateUser(userDto1);

		assertEquals(userEmail, updatedUserDto.email());
		assertEquals(PASSWORD1, userDto1.password());

	}


	@Test
	@DisplayName(USER_SERVICE_TEST + "ChangePassword")
	void testChangePassword() {
		String newPassword = "newPassword123";

		UserDto userWithNewPassword = userService.changePassword(userDto1.email(), newPassword);

		assertNotNull(userWithNewPassword);
		assertEquals(newPassword, userWithNewPassword.password());

	}
	@Test
	@DisplayName(USER_SERVICE_TEST + "DeleteUser")
	void testDeleteUser() {

		userService.deleteUser("user1@example.com");
		assertThrowsExactly(NoUserInRepositoryException.class, () -> userService.deleteUser("Introduction to Java"));

	}

	@Test
	@DisplayName(USER_SERVICE_TEST + "AllUsers")
	void testAllUsers() {
		List<UserDto> listOfUsers = userService.allUsers();
		List<String> listOfUsernames = listOfUsers.stream().map(UserDto::username)
				.toList();
		assertEquals(ALL_USERS_NICKNAMES, listOfUsernames);
	}

	
}
