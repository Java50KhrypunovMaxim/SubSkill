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
import subskill.subskill.models.Admin;


import subskill.subskill.models.User;
import subskill.subskill.repository.AdminRepository;
import subskill.subskill.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService, ValidationConstants {

	private final UserRepository userRepository;
	private final AdminRepository adminRepository;

	@Autowired
	public UserServiceImplementation(UserRepository userRepository, AdminRepository adminRepository) {
		this.userRepository = userRepository;
		this.adminRepository = adminRepository;
	}

	@Override
	public UserDto registerUser(UserDto userDto) throws UserExistingEmailExeption {
		User newUser = new User();
		BeanUtils.copyProperties(userDto, newUser);
		User savedUser = userRepository.save(newUser);
		return convertToUserDto(savedUser);
	}

	@Override
	public AdminDto registerAdmin(AdminDto adminDto) throws UserExistingEmailExeption {
		Admin newAdmin = new Admin();
		BeanUtils.copyProperties(adminDto, newAdmin);
		Admin savedAdmin = adminRepository.save(newAdmin);
		return convertToAdminDto(savedAdmin);

	}


	@Override
	public UserDto updateUser(UserDto userDto) throws NotFoundException {

		if (userDto == null || userDto.email() == null) {
			throw new IllegalArgumentException(INVALID_INPUT_DATA);
		}
		User existingUser = userRepository.findByEmail(userDto.email())
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		BeanUtils.copyProperties(userDto, existingUser, "id", "email");
		User updatedUser = userRepository.save(existingUser);
		return convertToUserDto(updatedUser);
	}

	@Override
	public UserDto changePassword(UserDto userDto, String newPassword) throws NoUserInRepositoryException, NotFoundException {
		if (userDto == null || newPassword == null) {
			throw new IllegalArgumentException(INVALID_INPUT_DATA);
		}

		User existingUser = userRepository.findByEmail(userDto.email())
				.orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
		existingUser.setPassword(newPassword);
		User updatedUser;
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
		return userRepository.findAll().stream().map(User::getEmail).collect(Collectors.toList());
	}

	@Override
	public List<String> allAdmins() {
		return adminRepository.findAll().stream().map(Admin::getEmail).collect(Collectors.toList());
	}

	@Override
	public AdminDto convertToAdminDto(Admin admin) {
		return new AdminDto(
				admin.getUsername(),
				admin.getPassword(),
				admin.getEmail(),
				admin.getStatus(),
				admin.getImageUrl(),
				admin.getRole()
		);
	}

		@Override
	public UserDto convertToUserDto(User user) {
		return new UserDto(
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				user.getNickname(),
				user.getStatus(),
				user.getImageUrl(),
				user.getRole()
		);
	}
}