package com.eazybank.accounts.serviceImpl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eazybank.accounts.dto.AccountsDto;
import com.eazybank.accounts.dto.CardsDto;
import com.eazybank.accounts.dto.CustomerDetailsDto;
import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.dto.LoansDto;
import com.eazybank.accounts.entity.Accounts;
import com.eazybank.accounts.entity.Customer;
import com.eazybank.accounts.exception.ResourceNotFoundException;
import com.eazybank.accounts.mapper.AccountsMapper;
import com.eazybank.accounts.mapper.CustomerMapper;
import com.eazybank.accounts.repository.AccountsRepository;
import com.eazybank.accounts.repository.CustomerRepository;
import com.eazybank.accounts.service.ICustomerService;
import com.eazybank.accounts.service.client.cardsFeignClient;
import com.eazybank.accounts.service.client.loansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

	private AccountsRepository accountsRepository;	
	private CustomerRepository customerRepository;	
	private loansFeignClient loansClient;	
	private cardsFeignClient cardsClient;
	
	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
		 Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
	                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
	        );
	        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
	                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
	        );
	        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
	        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
	        
	        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customerDto, new CustomerDetailsDto());
	        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
	        
	        ResponseEntity<LoansDto> fetchLoanDetails = loansClient.fetchLoanDetails(mobileNumber);  
	        customerDetailsDto.setLoansDto(fetchLoanDetails.getBody());
	        
	        ResponseEntity<CardsDto> fetchCardDetails = cardsClient.fetchCardDetails(mobileNumber);
	        customerDetailsDto.setCardsDto(fetchCardDetails.getBody());
	        
		return customerDetailsDto;
	}

}
