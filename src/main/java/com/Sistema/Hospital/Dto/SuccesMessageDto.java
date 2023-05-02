package com.Sistema.Hospital.Dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuccesMessageDto {
	private Integer statusCode;
	private Date timestamp;
	private String message;
}
