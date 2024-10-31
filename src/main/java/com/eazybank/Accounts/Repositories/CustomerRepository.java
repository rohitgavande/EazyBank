package com.eazybank.Accounts.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybank.Accounts.Entities.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
	Optional<Customer> findByMobileNumber(String mobileNumber);
	
}
