package com.example.egringotts.user_account;

import com.example.egringotts.account.Account;
import com.example.egringotts.account.AccountRepository;
import com.example.egringotts.user.User;
import com.example.egringotts.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAccountService {
    private UserAccountRepository userAccountRepository;
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    @Autowired
    public UserAccountService(UserAccountRepository userAccountRepository, UserRepository userRepository, AccountRepository accountRepository) {
        this.userAccountRepository = userAccountRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    public UserAccountService(UserAccountRepository userAccountRepository){
        this.userAccountRepository = userAccountRepository;
    }

    public List<UserAccount> getFavouritesById(long id){
        return userAccountRepository.findFavouritesById(id);
    }

    public void addNewFavourites(long userId, long accountId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("This account does not exist"));

        if(userAccountRepository.existsByUserAndAccount(user, account)){
            throw new IllegalArgumentException("This account already exist in your list");
        }

        UserAccountKey key = new UserAccountKey(userId, accountId);
        UserAccount newFavourite = new UserAccount(key, user, account);
        userAccountRepository.save(newFavourite);
    }

    public void deleteFavourite(long userId, long accountId){
        userAccountRepository.deleteFavourite(userId, accountId);
    }
}
