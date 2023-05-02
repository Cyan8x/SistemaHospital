package com.Sistema.Hospital.Exception;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectNotValidException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private final Set<String> errors = null;
	
}
