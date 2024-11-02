package com.eazybank.Accounts.ServiceImpl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.eazybank.Accounts.Dto.AccountsDto;
import com.eazybank.Accounts.Dto.CustomerDto;
import com.eazybank.Accounts.Entities.Accounts;
import com.eazybank.Accounts.Entities.Customer;
import com.eazybank.Accounts.Exception.CustomerAlreadyExistsException;
import com.eazybank.Accounts.Exception.ResourceNotFoundException;
import com.eazybank.Accounts.Repositories.AccountRepository;
import com.eazybank.Accounts.Repositories.CustomerRepository;
import com.eazybank.Accounts.Service.IAccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

	CustomerRepository customerRepository;
	AccountRepository accountRepository;

	@Override
	public void createAccount(CustomerDto customerDto) {

		Customer customerDomain = new Customer();
		BeanUtils.copyProperties(customerDto, customerDomain);
		
		Optional<Customer> byMobileNumber = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if (byMobileNumber.isPresent()) {
			throw new CustomerAlreadyExistsException(
					"Customer already Exists with given mobile no " + byMobileNumber.get().getMobileNumber());
		}
		
		customerDomain.setCreatedAt(LocalDateTime.now());
		customerDomain.setCreatedBy("Annonymous");
		Customer customer = customerRepository.save(customerDomain);
		accountRepository.save(createNewAccount(customer));
	}

	private Accounts createNewAccount(Customer customer) {
		Accounts accounts = new Accounts();
		accounts.setCustomerId(customer.getCustomerId());
		long randomAccouontNumber = 1000000000L + new Random().nextInt(900000000);

		accounts.setAccountNumber(randomAccouontNumber);
		accounts.setAccountType("Savings");
		accounts.setBranchAddress("123 ,Main street, Thane");
		accounts.setCreatedAt(LocalDateTime.now());
		accounts.setCreatedBy("Annonymous");
		return accounts;

	}

	@Override
	public CustomerDto fetchAccount(String mobileNumber) {
		
		CustomerDto customerDto = new CustomerDto();
		try {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

		Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "customerID",
						customer.getCustomerId().toString()));

		//setting customer dto with customer and accounts details
		BeanUtils.copyProperties(customer,customerDto);
		
		AccountsDto accountsDto = new AccountsDto();
		BeanUtils.copyProperties(accounts, accountsDto);
		
		customerDto.setAccountsDto(accountsDto);
		}catch(Exception e) {
			log.error(e.getMessage());
			throw new ResourceNotFoundException(e.getMessage());
		}
		return customerDto;
	}

}
