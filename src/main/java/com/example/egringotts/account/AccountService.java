package com.example.egringotts.account;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }

    public void addNewAccount(Account account){
        accountRepository.save(account);
    }

    @Transactional
    public void updateAccount(long id, long new_accountId) {
        Account oldAccount = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));

        if (new_accountId > 0) {
            oldAccount.setId(new_accountId);

        } else {
            throw new NoSuchElementException("No such Id!");
        }
    }

    @Transactional
    public Account getAccountById(long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // document
    public void updateKnutBalance(long id, double newValue){
        accountRepository.updateKnutBalanceByMyUser_Id(newValue, id);
    }

}
