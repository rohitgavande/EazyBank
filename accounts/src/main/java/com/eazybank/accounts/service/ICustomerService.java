package com.eazybank.accounts.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.eazybank.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

	public CustomerDetailsDto fetchCustomerDetails(
			@RequestParam String mobileNumber);
}
