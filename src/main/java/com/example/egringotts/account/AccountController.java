package com.example.egringotts.account;

import com.example.egringotts.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountService accountService, AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        this.accountService = accountService;
    }

    @GetMapping(path="/all")
    public @ResponseBody List<Account> getAllAccounts(){
        return (List<Account>) accountService.getAllAccounts();
    }

    @PostMapping(path="/create")
    public void createNewUsers(@RequestBody Account account){
        accountService.addNewAccount(account);
    }
}
