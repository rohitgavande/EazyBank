package com.eazybank.Accounts.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Customer", description ="Schema to hold customer and account details")

public class CustomerDto {

		@Schema(description ="name of the customer" , example = "Rohit Gavande") // to provide each field description and example if needed for better documentation 
		@NotEmpty(message = "Name cannot be null") 
		@Size(min = 5, max = 30, message = "Name should be between 5 to 30 characters")
	    private String name;

		@NotEmpty(message = "Email cannot be null")
		@Email
	    private String email;

		@Pattern(regexp = "(^$|[0-9]{10})", message ="Mobile Number must be 10 digit")
	    private String mobileNumber;
	    
	    private AccountsDto accountsDto;
}
