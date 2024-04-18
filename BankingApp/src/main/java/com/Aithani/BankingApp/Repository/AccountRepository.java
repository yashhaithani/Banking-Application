package com.Aithani.BankingApp.Repository;

import com.Aithani.BankingApp.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> // Entity Name & Primary Key type
{
    //AccountRepository will get CRUDE database methods
}
