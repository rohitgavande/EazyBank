package com.eazybank.accounts.mapper;

import com.eazybank.accounts.dto.CustomerDetailsDto;
import com.eazybank.accounts.dto.CustomerDto;
import com.eazybank.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto) {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }

    public static CustomerDetailsDto mapToCustomerDetailsDto(CustomerDto customerDto, CustomerDetailsDto customerDetailsDto) {
    	customerDetailsDto.setName(customerDto.getName());
    	customerDetailsDto.setEmail(customerDto.getEmail());
    	customerDetailsDto.setMobileNumber(customerDto.getMobileNumber());
        return customerDetailsDto;
    }
}
