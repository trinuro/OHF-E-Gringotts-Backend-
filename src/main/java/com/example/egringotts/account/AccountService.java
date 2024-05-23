package com.example.egringotts.account;

import com.example.egringotts.user.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
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
    public void updateAccount(long id,
                              Double knut_balance,
                              Double sickle_balance,
                              Double galleon_balance, Long user_id_long, User myUser) {
        Account currAccount = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));


        if (knut_balance != null && knut_balance > 0) {
            currAccount.setKnut_balance(knut_balance);
        }

        if (sickle_balance != null && sickle_balance > 0) {
            currAccount.setSickle_balance(sickle_balance);
        }

        if (galleon_balance != null && galleon_balance > 0) {
            currAccount.setGalleon_balance(galleon_balance);
        }

        if (user_id_long != null && !Objects.equals(currAccount.getUser_id_long(), user_id_long)) {
            currAccount.setUser_id_long(user_id_long);
        }

        if (myUser != null) {
            currAccount.setMyUser(myUser);
        }

    }

    public Account getAccountById(long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
