package com.Sistema.Hospital.Exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceAlreadyExist extends RuntimeException{
	private static final long serialVersionUID = 1L;

	private String resourceName;
	private String fieldName;
	private String fieldValue;
	private HttpStatus status;

	public ResourceAlreadyExist(String resourceName, String fieldName, String fieldValue) {
		super(String.format("Ya existe un(a) %s registrado en el sistema con %s: '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.status = HttpStatus.BAD_REQUEST;
	}
}
