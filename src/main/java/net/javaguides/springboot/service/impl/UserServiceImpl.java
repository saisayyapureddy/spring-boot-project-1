package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExist;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {

		// Convert UserDto into User JPA Entity
		// User user = UserMapper.mapToUser(userDto);

		// using MapperModel frame work class
		try {
			Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
			if (optionalUser.isPresent()) {
				throw new EmailAlreadyExist("Email already Exist in DB, please try with diff one");
			}
		} catch (DataIntegrityViolationException ex) {
			// Log the error
			log.error("Data integrity violation: " + ex.getMessage());
			// Provide a user-friendly error message
			// Rollback the transaction if necessary
		}

		User user = modelMapper.map(userDto, User.class);
		User savedUser = userRepository.save(user);

		// Convert User JPA entity to UserDto
		UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);

		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		// User user = optionalUser.get();
		return modelMapper.map(user, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepository.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(Long userId) {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		userRepository.deleteById(userId);

	}
}
