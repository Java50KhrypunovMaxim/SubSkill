package subskill.subskill.service;

import java.util.List;

import subskill.subskill.dto.AdminDto;
import subskill.subskill.dto.UserDto;


public interface UsersService {
	UserDto registerUser(UserDto userDto);
	AdminDto registerAdmin(AdminDto adminDto);
	UserDto updateUser(UserDto userDto);
	AdminDto changeAdminPassword (String email);
	UserDto deleteUser(String email);
	UserDto changePassword (UserDto userDto);
	AdminDto deleteAdmin(String email);
	List<String> allUsers();
	List<String> allAdmins();
	}
