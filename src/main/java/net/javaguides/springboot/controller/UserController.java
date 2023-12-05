package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javaguides.springboot.dto.UserDto;
import net.javaguides.springboot.service.UserService;

@Tag(

		name = "CRUD Rest APIs", description = "CRUD Rest APIs  Create User , Read User,Update User,Delete User")
@RestController
@AllArgsConstructor
@RequestMapping("api/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	@Operation(summary = "Create User rest Api", description = "Create user rest api is used to save user in database")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "User is created successfully"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	// build create User REST API
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@Operation(summary = "Get User By ID rest Api", description = "Get User By ID  rest api is used to Get the  user from  database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })

	// build get user by id REST API
	// http://localhost:8080/api/users/1
	@GetMapping("getUser/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
		UserDto user = userService.getUserById(userId);
		log.info("identified user is" + user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@Operation(summary = "Get ALL Users  rest Api", description = "Get ALL Users rest api is used to Get the all users  from  database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	// Build Get All Users REST API
	// http://localhost:8080/api/users
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Operation(summary = "Update User by id  rest Api", description = "Update User by id  rest api is used to update the existing user in database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User is updated successfully"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })

	// Build Update User REST API
	@PutMapping("update/{id}")
	// http://localhost:8080/api/users/1
	public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @Valid @RequestBody UserDto user) {
		user.setId(userId);
		UserDto updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@Operation(summary = "Delete User by id  rest Api", description = "Delete User by id  rest api is used to remove user from  database")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User is removed  successfully"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	// Build Delete User REST API
	@DeleteMapping("remove/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}

	@GetMapping("getUser")
	public ResponseEntity<UserDto> getUserByIdUsingRequestParam(@RequestParam(name = "id") Long userId) {
		UserDto user = userService.getUserById(userId);
		log.info("identified user is" + user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	/*
	 * @ExceptionHandler(ResourceNotFoundException.class) public
	 * ResponseEntity<ErroDetail>
	 * handlerResourceNotFoundExcp(ResourceNotFoundException exception, WebRequest
	 * webRequest) { ErroDetail erroDetail = new ErroDetail(
	 * 
	 * LocalDateTime.now(), exception.getMessage(),
	 * webRequest.getDescription(false), "USER_NOT_FOUND");
	 * 
	 * return new ResponseEntity<>(erroDetail, HttpStatus.NOT_FOUND); }
	 */

}
