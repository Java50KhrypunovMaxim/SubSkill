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


	private static final String EMAIL1 = "user1@telran.com";
	private static final String EMAIL2 = "name1@tel-ran.co.il";
	private static final String EMAIL3 = "Max@gmail.com";



	private static final String PASSWORD1 = "telran321";
	private static final String PASSWORD2 = "tel567";
	private static final String PASSWORD3 = "telran234";

	private static final String IMAGEURL1 = "https://example.com/image1.jpg";
	private static final String IMAGEURL2 = "https://example.com/image2.jpg";
	private static final String IMAGEURL3 = "https://example.com/image3.jpg";

	UserDto userDto1 = new UserDto(USERNAME1, EMAIL1, PASSWORD1, true, IMAGEURL1, Roles.USER);
	UserDto userDto2 = new UserDto(USERNAME2, EMAIL2, PASSWORD2, true, IMAGEURL2, Roles.USER);
	UserDto userDto3 = new UserDto(USERNAME3, EMAIL3, PASSWORD3, true, IMAGEURL3, Roles.USER);

	public static final List<String> ALLUSERS = Arrays.asList("user1@example.com", "user2@example.com",
			"user3@example.com", "user4@example.com", "user5@example.com");

	@Autowired
	UserRepository userRepo;

	@Autowired
	UserService userService;;
	@Test
	void testUpdateAndDeleteUser() {

		Optional<User> optionalUser = userRepo.findByEmail(userDto1.email());
		assertTrue(optionalUser.isPresent());

		UserDto updatedUserDto = new UserDto(userDto2.username(), userDto1.email(), userDto2.password(), userDto2.online(), userDto2.imageUrl(), userDto2.role());
		User updatedUser = userRepo.save(User.builder()
				.username(updatedUserDto.username())
				.password(updatedUserDto.password())
				.email(updatedUserDto.email())
				.online(updatedUserDto.online())
				.imageUrl(updatedUserDto.imageUrl())
				.role(updatedUserDto.role())
				.build());
		assertNotNull(updatedUser);

		User updatedDbUser = userRepo.findByEmail(updatedUserDto.email()).orElse(null);
		assertNotNull(updatedDbUser);
		assertEquals(updatedUserDto.username(), updatedDbUser.getUsername());

		userRepo.delete(updatedDbUser);
		Optional<User> deletedUser = userRepo.findByEmail(updatedUserDto.email());
		assertTrue(deletedUser.isEmpty());
	}
	@Test
	void testExist() {
		Optional<User> optionalUser = userRepo.findByEmail(EMAIL2);
        assertTrue(optionalUser.isPresent());
        User user = optionalUser.get();
        assertEquals(USERNAME2, user.getUsername());
	}
}
