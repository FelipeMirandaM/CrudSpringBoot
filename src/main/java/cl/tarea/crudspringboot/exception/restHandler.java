package cl.tarea.crudspringboot.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cl.tarea.crudspringboot.model.ErrorResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class restHandler {

	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		return new ResponseEntity<>(processFieldErrors(fieldErrors), HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
		String result = ex.getMessage();
		return new ResponseEntity<>(processString(result), HttpStatus.BAD_REQUEST);
	}

	private ErrorResponse processFieldErrors(List<FieldError> fieldErrors) {
		ErrorResponse error = new ErrorResponse("Error", BAD_REQUEST.value());
		for (FieldError fieldError : fieldErrors) {
			error.addError(fieldError.getDefaultMessage());
		}
		return error;
	}
	private ErrorResponse processString(String errorMessage) {
		ErrorResponse error = new ErrorResponse("Error", BAD_REQUEST.value());
		error.addError(errorMessage);
		return error;
	}

}
