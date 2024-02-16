package com.subskill.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.subskill.dto.UserDto;
import com.subskill.enums.Roles;
import com.subskill.models.User;
import com.subskill.repository.UserRepository;

@SpringBootTest
@Sql(scripts = {"classpath:data_for_the_database.sql"})
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

	UserDto userDto1 = new UserDto(USERNAME1, PASSWORD1, EMAIL4, true, IMAGEURLl, Roles.USER);
	UserDto userDto2 = new UserDto(USERNAME2, PASSWORD2, EMAIL2, true, IMAGEURL2, Roles.USER);
	UserDto userDto3 = new UserDto(USERNAME3, PASSWORD3, EMAIL1, true, IMAGEURL3, Roles.USER);
	UserDto userDtoUpdate = new UserDto(USERNAME2, PASSWORD2, EMAIL2, true, IMAGEURL2, Roles.ADMIN);

	public static final List<String> ALLUSERS = Arrays.asList("user1@example.com", "user2@example.com",
			"user3@example.com", "user4@example.com", "user5@example.com");

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserService userService;

	@Test
	void testExist() {
		Optional<User> optionalUser = userRepo.findByEmail("john.doe@example.com");
        assertTrue(optionalUser.isPresent());

        User user = optionalUser.get();
        assertEquals("john_doe", user.getUsername());
	}
}
