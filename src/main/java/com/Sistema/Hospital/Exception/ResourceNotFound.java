package com.Sistema.Hospital.Exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String resourceName;
	private String fieldName;
	private long fieldValue;
	private HttpStatus status;

	public ResourceNotFound(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s no encontrado con el %s: '%s'", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.status = HttpStatus.NOT_FOUND;
	}
}
