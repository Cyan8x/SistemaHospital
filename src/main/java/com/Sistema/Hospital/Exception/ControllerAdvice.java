package com.Sistema.Hospital.Exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.Sistema.Hospital.Dto.ErrorMessageDto;
import com.Sistema.Hospital.Dto.ErrorsFromBadRequestDto;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler{

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorMessageDto> AnyErrorExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessageDto errorMessageDto = ErrorMessageDto.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).timestamp(new Date())
				.message(ex.getMessage()).description(request.getDescription(false)).build();
		return new ResponseEntity<ErrorMessageDto>(errorMessageDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = ResourceNotFound.class)
	public ResponseEntity<ErrorMessageDto> ResourceNotFoundExceptionHandler(ResourceNotFound ex, WebRequest request) {
		ErrorMessageDto errorMessageDto = ErrorMessageDto.builder().statusCode(ex.getStatus().value()).timestamp(new Date()).message(ex.getMessage())
				.description(request.getDescription(false)).build();
		return new ResponseEntity<ErrorMessageDto>(errorMessageDto, ex.getStatus());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message   = error.getDefaultMessage();
			errors.put(fieldName, message);
		});

		ErrorsFromBadRequestDto errorsFromBadRequestDto = ErrorsFromBadRequestDto.builder().statusCode(HttpStatus.BAD_REQUEST.value())
				.timestamp(new Date()).errors(errors).description(request.getDescription(false)).build();

		return new ResponseEntity<>(errorsFromBadRequestDto, HttpStatus.BAD_REQUEST);
	}
}
