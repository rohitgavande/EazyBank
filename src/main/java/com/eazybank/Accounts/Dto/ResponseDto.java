package com.eazybank.Accounts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {

	public ResponseDto(String string) {
		this.statusMsg = string;
	}

	private String statusCode;
	
	private String statusMsg;
}
