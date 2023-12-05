package net.javaguides.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExist extends RuntimeException {
	
	private String message;
	
	public EmailAlreadyExist(String message)
	{
		super(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
