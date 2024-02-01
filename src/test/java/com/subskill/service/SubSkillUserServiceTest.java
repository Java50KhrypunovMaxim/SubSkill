package com.subskill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.subskill.dto.UserDto;
import com.subskill.exception.NoUserInRepositoryException;
import com.subskill.exception.UserExistingEmailExeption;
import com.subskill.enums.Roles;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;

@SpringBootTest
@Sql(scripts = { "classpath:users.sql" })
class SubSkillUserServiceTest {
	private static final String USER_SERVICE_TEST = "User Service Test: ";

	private static final String USERNAME1 = "MAX";
	private static final String USERNAME2 = "Artur";
	private static final String USERNAME3 = "David";

	private static final String NICKNAME1 = "Rambo";
	private static final String NICKNAME2 = "Leo";
	private static final String NICKNAME3 = "Monkey";

	private static final String EMAIL1 = "user1@telran.com";
	private static final String EMAIL2 = "name1@tel-ran.co.il";
	private static final String EMAIL4 = "Max@gmail.com";

	private static final String PASSWORD1 = "telran321";
	private static final String PASSWORD2 = "tel567";
	private static final String PASSWORD3 = "telran234";

	private static final String IMAGEURLl = "https://example.com/image1.jpg";
	private static final String IMAGEURL2 = "https://example.com/image2.jpg";
	private static final String IMAGEURL3 = "https://example.com/image3.jpg";

	UserDto userDto1 = new UserDto(USERNAME1, PASSWORD1, EMAIL4, NICKNAME1, true, IMAGEURLl, Roles.USER);
	UserDto userDto2 = new UserDto(USERNAME2, PASSWORD2, EMAIL2, NICKNAME2, true, IMAGEURL2, Roles.USER);
	UserDto userDto3 = new UserDto(USERNAME3, PASSWORD3, EMAIL1, NICKNAME3, true, IMAGEURL3, Roles.USER);
	UserDto userDtoUpdate = new UserDto(USERNAME2, PASSWORD2, EMAIL2, "Magnus", true, IMAGEURL2, Roles.ADMIN);

	public static final List<String> ALLUSERS = Arrays.asList("user1@example.com", "user2@example.com",
			"user3@example.com", "user4@example.com", "user5@example.com");

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;


	@Test
	@DisplayName(USER_SERVICE_TEST + SubSkillTestNameUserService.SHOW_ALL_USER)
	void testShowAllUsers() {
		List<String> actualUsers = userService.allUsers();
		assertIterableEquals(ALLUSERS, actualUsers);
	}

	@Test
	@DisplayName(USER_SERVICE_TEST + SubSkillTestNameUserService.REGISTER_USER)
	void testRegisterUser() {
		assertEquals(userDto1, userService.registerUser(userDto1));
		assertThrowsExactly(UserExistingEmailExeption.class, () -> userService.registerUser(userDto1));
		User user = userRepo.findByEmail(userDto1.email()).orElse(null);
		assertEquals(userDto1, user.build());

	}

	@Test
	@DisplayName(USER_SERVICE_TEST + SubSkillTestNameUserService.DELETE_USER)
	void testDeleteUser() {
		userService.registerUser(userDto2);
		userService.deleteUser(userDto2.email());
		assertThrowsExactly(NoUserInRepositoryException.class, () -> userService.deleteUser(userDto2.email()));
	}

	@Test
	@DisplayName(USER_SERVICE_TEST + SubSkillTestNameUserService.UPDATE_USER)
	void testUpdateUser() {
		userService.registerUser(userDto2);
		UserDto userUpdated = new UserDto(USERNAME2, PASSWORD2, EMAIL2, "Magnus", true, IMAGEURL2, Roles.ADMIN);
		assertEquals(userUpdated, userService.updateUser(userUpdated));
		assertEquals("Magnus", userRepo.findByEmail(EMAIL2).get().getNickname());
	}

	@Test
	@DisplayName(USER_SERVICE_TEST + SubSkillTestNameUserService.CHANGE_PASSWORD_USER)
	void testChangePasswordOfUser() {
		userService.registerUser(userDto3);
		userService.changePassword(EMAIL1, "Max1989");
		assertEquals("Max1989", userRepo.findByEmail(EMAIL1).get().getPassword());
	}

}
