package com.Sistema.Hospital.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.Sistema.Hospital.Dto.ErrorMessageDto;

@RestControllerAdvice
public class ControllerAdvice {

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = ResourceNotFound.class)
	public ResponseEntity<ErrorMessageDto> ResourceNotFoundExceptionHandler(ResourceNotFound ex, WebRequest request) {
		ErrorMessageDto errorMessageDto = ErrorMessageDto.builder().statusCode(ex.getStatus().value())
				.timestamp(new Date()).message(ex.getMessage()).description(request.getDescription(false)).build();
		return new ResponseEntity<ErrorMessageDto>(errorMessageDto, ex.getStatus());
	}
}
