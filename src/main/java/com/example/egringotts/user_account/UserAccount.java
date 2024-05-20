package com.example.egringotts.user_account;

import com.example.egringotts.account.Account;
import com.example.egringotts.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_account")
public class UserAccount {
    @EmbeddedId
    private UserAccountKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private Account account;

    public UserAccount(){}

    public UserAccount(UserAccountKey id, User user, Account account) {
        this.id = id;
        this.user = user;
        this.account = account;
    }

    public UserAccountKey getId() {
        return id;
    }

    public void setId(UserAccountKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
