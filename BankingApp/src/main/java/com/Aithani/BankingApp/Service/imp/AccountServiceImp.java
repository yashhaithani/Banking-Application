package com.Aithani.BankingApp.Service.imp;

import com.Aithani.BankingApp.Entity.Account;
import com.Aithani.BankingApp.Mapper.AccountMapper;
import com.Aithani.BankingApp.Repository.AccountRepository;
import com.Aithani.BankingApp.Service.AccountService;
import dto.AccountDto;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class AccountServiceImp implements AccountService
{
    private AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id)
    {
        Account account = accountRepository.findById(id).orElseThrow(()->new RuntimeException("Account doesn't Exist"));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount)
    {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account doesn't Exist"));

        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save((account));
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount)
    {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account doesn't Exist"));

        if(account.getBalance()<amount)
        {
            throw new RuntimeException("Insufficient Amount");
        }
        double total = account.getBalance()-amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account))
                .collect((Collectors.toList()));
    }

    @Override
    public void deleteAccount(long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account doesn't Exist"));

        accountRepository.deleteById(id);
    }

    @Override
    public AccountDto transfer(Long idTarget, Long idSource, double amount) {
        AccountDto sourceAccountDto = getAccountById(idSource);
        double sourceBalance = sourceAccountDto.getBalance();
        if (sourceBalance < amount) {
            throw new RuntimeException("Insufficient funds in the source account");
        }
        AccountDto sourceUpdatedAccount = withdraw(idSource, amount);
        AccountDto targetUpdatedAccount = deposit(idTarget, amount);
        return targetUpdatedAccount;
    }

    @Override
    public AccountDto quickLoan(Long id, double amount) {
        AccountDto accountDto = getAccountById(id);
        double balance = accountDto.getBalance();

        if (balance < amount * (2.0 / 3.0)) {
            throw new RuntimeException("Insufficient Amount");
        }

        AccountDto updatedAccount = deposit(id, amount);
        return updatedAccount;
    }

}
