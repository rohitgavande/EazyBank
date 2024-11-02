package com.eazybank.Accounts.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybank.Accounts.Entities.Accounts;
import java.util.List;
import java.util.Optional;


@Repository
public interface AccountRepository extends JpaRepository<Accounts,Long>{

	 Optional<Accounts> findByCustomerId(Long customerId);
	
}
