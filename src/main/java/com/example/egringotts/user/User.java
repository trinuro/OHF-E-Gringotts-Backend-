package com.example.egringotts.user;

import com.example.egringotts.account.Account;
import com.example.egringotts.transaction.Transaction;
import com.example.egringotts.user_account.UserAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Class that describes users of our system
 * @param <T>
 */
@Entity
@Table(name = "user")
public class User<T extends AbstractUser> {
    @Id
//    @SequenceGenerator(
//            name = "user_sequence",
//            sequenceName = "user_sequence",
//            allocationSize = 1,
//            initialValue = 2
//    )
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long id;
    private String name;
    private String email;
    private String password;
    private String status;
    // A user can have many accounts
    @OneToMany(mappedBy = "myUser")
    private List<Account> accounts;
    @OneToMany(mappedBy = "user")
    private Set<UserAccount> favourites;

    @Column(name = "phone_number")
    private String phoneNumber;

    // Default constructor
    public User(){
        // Don't construct user without appropriate parameters
        // Here just because Spring Boot needs it :(
    }

    // Constructor
    public User(long id, String name, String email, String password, String phoneNumber, Set<UserAccount> favourites) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = "";
        this.phoneNumber = phoneNumber;
        this.favourites = favourites;
    }

    public User(User other, T userType) {
        this.id = other.getId();
        this.name = other.getName();
        this.email = other.getEmail();
        this.password = other.getPassword();
        this.status = userType.getType();
        this.phoneNumber = other.getPhoneNumber();
        System.out.println(userType);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Getter and setter methods
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Tostring methods that returns information about this object in JSON format
     * @return information about this object in JSON format
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
