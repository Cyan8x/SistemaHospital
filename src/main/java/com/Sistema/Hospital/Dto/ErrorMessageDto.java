package com.Sistema.Hospital.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDto {
	private Integer statusCode;
	private Date timestamp;
	private String message;
	private String description;
}
