package subskill.subskill.service;

import java.util.List;

import subskill.subskill.dto.AdminDto;
import subskill.subskill.dto.UserDto;
import subskill.subskill.models.Admins;
import subskill.subskill.models.Users;


public interface UsersService {
	UserDto registerUser(UserDto userDto);
	AdminDto registerAdmin(AdminDto adminDto);
	UserDto updateUser(UserDto userDto);
	UserDto changePassword (UserDto userDto, String newPassword);
	UserDto deletePerson(String email);
	AdminDto deleteAdmin(String email);
	List<String> allUsers();
	List<String> allAdmins();

	AdminDto convertToAdminDto(Admins admins);

	UserDto convertToUserDto(Users user);
}

