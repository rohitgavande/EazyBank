package com.eazybank.Accounts.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

	@NotEmpty( message = "Account number cannot be empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message ="Account Number must be 10 digit")
	private Long accountNumber;

	@NotEmpty(message = "accountType cannot be empty")
	private String accountType;

	@NotEmpty(message = "Branch address cannot be empty")
	private String branchAddress;
}
