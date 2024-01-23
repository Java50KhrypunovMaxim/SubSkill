package subskill.subskill.service;

import java.util.List;

import subskill.subskill.dto.AdminDto;
import subskill.subskill.dto.UserDto;
import subskill.subskill.models.Admins;
import subskill.subskill.models.User;

public interface UserService {
	UserDto registerUser(UserDto userDto);
	UserDto updateUser(UserDto userDto);
	UserDto changePassword (UserDto userDto, String newPassword);
	UserDto deletePerson(String email);
	List<String> allUsers();
	UserDto convertToUserDto(User user);
}

