package com.Sistema.Hospital.Exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeNotHaveToBeNull  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String attributeName;
	private HttpStatus status;

	public AttributeNotHaveToBeNull(String attributeName) {
		super(String.format("%s no debe(n) ser nulo(s).", attributeName));
		this.attributeName = attributeName;
		this.status = HttpStatus.BAD_REQUEST;
	}
}
