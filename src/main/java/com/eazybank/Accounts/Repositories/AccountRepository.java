package com.eazybank.Accounts.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eazybank.Accounts.Entities.Accounts;


@Repository
public interface AccountRepository extends JpaRepository<Accounts,Long>{

	 Optional<Accounts> findByCustomerId(Long customerId);
	 
	 @Modifying
	 @Query("Delete from Accounts a where a.customerId = :customerId")
	 void deletByCustomerId(@Param("customerId") Long customerId);
	
}
