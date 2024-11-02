package com.eazybank.Accounts.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazybank.Accounts.Constants.AccountsConstants;
import com.eazybank.Accounts.Constants.EndPointReference;
import com.eazybank.Accounts.Dto.CustomerDto;
import com.eazybank.Accounts.Dto.ResponseDto;
import com.eazybank.Accounts.Service.IAccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("api/v1/accounts")
@AllArgsConstructor
public class AccountsController {

	
	private IAccountService service;

	@PostMapping(EndPointReference.CREATE_ACCOUNT)
	public ResponseEntity<ResponseDto> createAccont(@RequestBody CustomerDto customerDto) {

		service.createAccount(customerDto);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.ACCOUNT_CREATED));

	}
	
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
		CustomerDto customerDto = service.fetchAccount(mobileNumber);
		
		return ResponseEntity.status(HttpStatus.OK).body(customerDto);
	}
	
	

}
