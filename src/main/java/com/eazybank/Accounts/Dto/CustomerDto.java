package com.eazybank.Accounts.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

	    private String name;

	    private String email;

	    private String mobileNumber;
	    
	    private AccountsDto accountsDto;
}
