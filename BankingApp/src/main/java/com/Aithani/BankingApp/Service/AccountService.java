package com.Aithani.BankingApp.Service;

import dto.AccountDto;

import java.util.List;

public interface AccountService
{
    AccountDto createAccount (AccountDto accountDto);

    AccountDto getAccountById(long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(long id);

    AccountDto transfer(Long idTarget,Long idSource, double amount);

    AccountDto quickLoan(Long id, double amount);
}
