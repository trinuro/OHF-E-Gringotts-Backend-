package com.example.egringotts.account;

import com.example.egringotts.user.User;
import jakarta.transaction.Transactional;
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


    @PutMapping(path="{accountId}")
    public void updateAccount(@PathVariable("accountId") long id,
                              @RequestParam(required = false) Double knut_balance,
                              @RequestParam(required = false) Double sickle_balance,
                              @RequestParam(required = false) Double galleon_balance,
                              @RequestParam(required = false) Long user_id_long,
                              @RequestParam(required = false) User myUser){
        accountService.updateAccount(id, knut_balance, sickle_balance, galleon_balance, user_id_long, myUser);
    }

    @GetMapping(path="/get")
    public Account getAccountById(@RequestParam(required = true) int id){
        return accountService.getAccountById(id);
    }

}
