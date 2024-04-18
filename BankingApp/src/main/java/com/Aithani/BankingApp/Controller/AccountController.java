package com.Aithani.BankingApp.Controller;

import com.Aithani.BankingApp.Service.AccountService;
import dto.AccountDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController
{
    private AccountService accountService;

    public AccountController(AccountService accountService)      //Constructor based dependency Injection
    {
        this.accountService = accountService;
    }

    //Add Account Rest API
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Get Account Rest API
    @GetMapping("/{id}")
    public  ResponseEntity<AccountDto> getAccountbyId(@PathVariable long id)
    {
        AccountDto accountDto = accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDto);
    }

    // Deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);
    }

    // Withdraw Rest API
    @PutMapping("/{id}/withdraw")
    public  ResponseEntity<AccountDto> withdraw(@PathVariable Long id,@RequestBody Map<String,Double>request){
        double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);

    }

    // Get All Account Rest API
    @GetMapping
    public  ResponseEntity<List<AccountDto>> getAllAccount()
    {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return  ResponseEntity.ok(accounts);
    }

    //Delete Account Rest API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id)
    {
        accountService.deleteAccount((id));
        return ResponseEntity.ok("Account is deleted successfully!");
    }
    @PutMapping("/{idTarget}/transfer/{idSource}")
    public ResponseEntity<String> transfer(@PathVariable Long idTarget, @PathVariable Long idSource, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        AccountDto transferredAccount = accountService.transfer(idTarget, idSource, amount);
        return ResponseEntity.ok("Account Transferred successfully!");
    }
    // Quick Loan REST API
    @PostMapping("/{id}/quick-loan")
    public ResponseEntity<AccountDto> quickLoan(@PathVariable Long id, @RequestParam double amount) {
        try {
            AccountDto updatedAccount = accountService.quickLoan(id, amount);
            return ResponseEntity.ok(updatedAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Handle the exception as needed
        }
    }
}
