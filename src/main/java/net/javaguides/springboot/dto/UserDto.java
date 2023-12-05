package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Schema(description = "UserDto Model Information")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	
	private Long id;
	@Schema(description = "User FirstName")
	@NotEmpty(message = "firstName should not be null or empty ")
	private String firstName;
	
	@Schema(description = "User LastName")
	@NotEmpty(message = "lastName should not be null or empty ")
	private String lastName;
	
	@Schema(description = "User Emai-id")
	@NotEmpty(message = "Email should not be null or empty ")
	@Email(message = "enter valid email id ")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

}
