package com.example.egringotts.account;

import com.example.egringotts.transaction.Transaction;
import com.example.egringotts.user_account.UserAccount;
import jakarta.persistence.*;
import com.example.egringotts.user.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double total_balance;

    // One account can only be owned by one user
    // @JoinColumn(name="user_id") states that there is no such column called myUser
    // But this column is called user_id
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id", insertable=false, updatable=false)
    private User myUser;
    private double knut_balance;
    private double sickle_balance;
    private double galleon_balance;
    @Column(name="user_id")
    private Long user_id_long;
    @OneToMany(mappedBy="sourceAccount")
    private List<Transaction> transactionsSent;
    @OneToMany(mappedBy = "destinationAccount")
    private List<Transaction> transactionsReceived;
    @OneToMany(mappedBy = "account")
    private Set<UserAccount> favourites;


    //Constructors
    public Account(long id, double total_balance, User myUser, double knut_balance, double sickle_balance, double galleon_balance, Long user_id_long, Set<UserAccount> favourites) {
        this.id = id;
        this.total_balance = total_balance;
        this.myUser = myUser;
        this.knut_balance = knut_balance;
        this.sickle_balance = sickle_balance;
        this.galleon_balance = galleon_balance;
        this.user_id_long = user_id_long;
        this.favourites = favourites;
    }

    public Account(){}

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(double total_balance) {
        this.total_balance = total_balance;
    }

    public User getMyUser() {
        return myUser;
    }

    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    public double getKnut_balance() {
        return knut_balance;
    }

    public void setKnut_balance(double knut_balance) {
        this.knut_balance = knut_balance;
    }

    public double getSickle_balance() {
        return sickle_balance;
    }

    public void setSickle_balance(double sickle_balance) {
        this.sickle_balance = sickle_balance;
    }

    public double getGalleon_balance() {
        return galleon_balance;
    }

    public void setGalleon_balance(double galleon_balance) {
        this.galleon_balance = galleon_balance;
    }

    public Long getUser_id_long() {
        return user_id_long;
    }

    public void setUser_id_long(Long user_id_long) {
        this.user_id_long = user_id_long;
    }
}
