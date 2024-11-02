package com.eazybank.Accounts.Service;

import com.eazybank.Accounts.Dto.CustomerDto;

public interface IAccountService {

	
	/*
	 * @Param - CustomerDto 
	 */
	void createAccount(CustomerDto customerDto);
	
	CustomerDto fetchAccount(String mobileNumber);
}
