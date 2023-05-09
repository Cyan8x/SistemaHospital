package com.Sistema.Hospital.Dto;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorsFromBadRequestDto {
	private Integer statusCode;
	private Date timestamp;
	private String description;
	private Map<String,String> errors;
}
