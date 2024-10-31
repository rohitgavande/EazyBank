package com.eazybank.Accounts.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazybank.Accounts.Entities.Accounts;

@Repository
public interface AccountRepository extends JpaRepository<Accounts,Long>{

	
}
