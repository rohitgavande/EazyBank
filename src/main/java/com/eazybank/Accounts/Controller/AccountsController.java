package com.eazybank.Accounts.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybank.Accounts.Constants.AccountsConstants;
import com.eazybank.Accounts.Constants.EndPointReference;
import com.eazybank.Accounts.Dto.CustomerDto;
import com.eazybank.Accounts.Dto.ResponseDto;
import com.eazybank.Accounts.Service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(
		name = "Crud Rest Api's For Accounts in EazyBank",
		description = "Crud rest APis in eazyBank to create,update,fetch and delete datails"
	)

@RestController
@Slf4j
@RequestMapping("api/v1/accounts")
@AllArgsConstructor
@Validated
public class AccountsController {

	private IAccountService service;

	@Operation(summary ="Create Account" , description ="Rest Api to create customer And his account inside eazy bank")
	@ApiResponse(responseCode = "201")
	@PostMapping(EndPointReference.CREATE_ACCOUNT)
	public ResponseEntity<ResponseDto> createAccont(@Valid @RequestBody CustomerDto customerDto) {

		service.createAccount(customerDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.ACCOUNT_CREATED));

	}

	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digit") String mobileNumber) {
		CustomerDto customerDto = service.fetchAccount(mobileNumber);

		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}

	@PutMapping("/update")
	public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean isUpdated = service.updateAccount(customerDto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(),"Account Updated"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto("Something went wrong while updating the details ! "));
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digit") String mobileNumber) {
		boolean isdeleted = service.deleteAccount(mobileNumber);
		if (isdeleted) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Account Deleted"));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto("Something went wrong while deleting the details ! "));
		}
	}

}
