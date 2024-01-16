package subskill.subskill.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import subskill.subskill.api.ValidationConstants;
import subskill.subskill.dto.AdminDto;
import subskill.subskill.dto.UserDto;
import subskill.subskill.exception.NoUserInRepositoryException;
import subskill.subskill.exception.NotFoundException;
import subskill.subskill.exception.UserExistingEmailExeption;
import subskill.subskill.models.Admins;
import subskill.subskill.models.Users;
import subskill.subskill.repository.AdminRepository;
import subskill.subskill.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersServiceImplementation implements UsersService, ValidationConstants {

	private final UserRepository userRepository;
	private final AdminRepository adminRepository;

	@Autowired
	public UsersServiceImplementation(UserRepository userRepository, AdminRepository adminRepository) {
		this.userRepository = userRepository;
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDto registerUser(UserDto userDto) throws UserExistingEmailExeption {
		Users newUser = new Users();
		BeanUtils.copyProperties(userDto, newUser);
		Users savedUser = userRepository.save(newUser);
		return convertToUserDto(savedUser);
	}

	@Override
	public AdminDto registerAdmin(AdminDto adminDto) throws UserExistingEmailExeption {
		Admins newAdmin = new Admins();
		BeanUtils.copyProperties(adminDto, newAdmin);
		Admins savedAdmin = adminRepository.save(newAdmin);
		return convertToAdminDto(savedAdmin);

	}


	@Override
	public UserDto updateUser(UserDto userDto) throws NotFoundException {

		if (userDto == null || userDto.getEmail() == null) {
			throw new IllegalArgumentException(INVALID_INPUT_DATA);
		}
		Users existingUser = userRepository.findByEmail(userDto.getEmail())
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		BeanUtils.copyProperties(userDto, existingUser, "id", "email");
		Users updatedUser = userRepository.save(existingUser);
		return convertToUserDto(updatedUser);
	}

	@Override
	public UserDto changePassword(UserDto userDto, String newPassword) throws NoUserInRepositoryException, NotFoundException {
		if (userDto == null || newPassword == null) {
			throw new IllegalArgumentException(INVALID_INPUT_DATA);
		}

		Users existingUser = userRepository.findByEmail(userDto.getEmail())
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		existingUser.setPassword(newPassword);
		Users updatedUser;
		try {
			updatedUser = userRepository.save(existingUser);
		} catch (Exception e) {
			throw new NoUserInRepositoryException("Error changing password: " + e.getMessage());
		}

		return convertToUserDto(updatedUser);
	}

	@Override
	public UserDto deletePerson(String email) {
		userRepository.findByEmail(email).ifPresent(userRepository::delete);
		return null;
	}

	@Override
	public AdminDto deleteAdmin(String email) {
		adminRepository.findByEmail(email).ifPresent(adminRepository::delete);
		return null;
	}

	@Override
	public List<String> allUsers() {
		return userRepository.findAll().stream().map(Users::getEmail).collect(Collectors.toList());
	}

	@Override
	public List<String> allAdmins() {
		return adminRepository.findAll().stream().map(Admins::getEmail).collect(Collectors.toList());
	}

	@Override
	public AdminDto convertToAdminDto(Admins admins) {
		AdminDto adminDto = new AdminDto();
		BeanUtils.copyProperties(admins, adminDto);
		return adminDto;
	}

	@Override
	public UserDto convertToUserDto(Users user) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}
}