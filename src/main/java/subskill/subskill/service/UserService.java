package subskill.subskill.service;

import java.util.List;

import subskill.subskill.dto.UserDto;
import subskill.subskill.models.User;

public interface UserService {
	UserDto registerUser(UserDto userDto);
	UserDto updateUser(UserDto userDto);
	UserDto changePassword (UserDto userDto, String newPassword);
	UserDto deleteUser(String email);
	List<String> allUsers();
	List<String> allAdmins();
	UserDto convertToUserDto(User user);
}

