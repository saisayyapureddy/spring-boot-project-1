package net.javaguides.springboot.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // to handle global exception we use @ControllerAdvice. If we want to use
					// specif// we use@ExceptionHandler class
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErroDetail> handlerResourceNotFoundExcp(ResourceNotFoundException exception,
			WebRequest webRequest) {
		ErroDetail erroDetail = new ErroDetail(

				LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false), "USER_NOT_FOUND");

		return new ResponseEntity<>(erroDetail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmailAlreadyExist.class)
	public ResponseEntity<ErroDetail> handlerEmailAlreadyExist(EmailAlreadyExist exception, WebRequest webRequest) {
		ErroDetail erroDetail = new ErroDetail(

				LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false), "EMAIL_ALREADY_EXIST");

		return new ResponseEntity<>(erroDetail, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroDetail> handlerEmailAlreadyExist(Exception exception, WebRequest webRequest) {
		ErroDetail erroDetail = new ErroDetail(

				LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false), "INTERNAL_SERVER_ERROR");

		return new ResponseEntity<>(erroDetail, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String, String> errors = new HashMap<>();
		List<ObjectError> errorsList = ex.getBindingResult().getAllErrors();

		// here for each void java.lang.Iterable.forEach(Consumer<? super ObjectError>
		// action)
		errorsList.forEach((err) -> {
			String fieldName = ((FieldError) err).getField();
			String message = err.getDefaultMessage();
			errors.put(fieldName, message);
		});

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

	}

}
