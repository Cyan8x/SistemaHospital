package com.Sistema.Hospital.Config;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.Sistema.Hospital.Exception.ObjectNotValidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class ObjectValidator<T> {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();

	public void validate(T objectToValidate) {
		Set<ConstraintViolation<T>> violations = validator.validate(objectToValidate);

		if (!violations.isEmpty()) {
			var errorMessage = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toSet());
			
			throw new ObjectNotValidException();
		}
	}
}
