package com.eazybank.Accounts.Dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

	private String apiPath;
	private HttpStatus errorCode;
	private String errorMsg;
	private LocalDateTime errortime;
}
