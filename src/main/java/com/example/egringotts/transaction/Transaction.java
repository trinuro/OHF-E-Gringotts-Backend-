package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import jakarta.persistence.*;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount;
    @Column(name="date_time")
    private String dateTime;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="source_account_id", insertable = false, updatable = false)
    private Account sourceAccount;
    @Column(name="source_account_id")
    private Long source_account_id_long;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="destination_account_id", insertable = false, updatable = false)
    private Account destinationAccount;
    @Column(name="destination_account_id")
    private Long destination_account_id_long;
    private String category;
    private String description;

    // constructors
    public Transaction(long id, double amount, String dateTime, String date_time_string, Account sourceAccount, Long source_account_id_long, Account destinationAccount, Long destination_account_id_long, String category, String description) {
        this.id = id;
        this.amount = amount;

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        this.dateTime = LocalDateTime.parse(date_time_string, formatter);
//        System.out.println(dateTime);
        this.dateTime = dateTime;
        this.sourceAccount = sourceAccount;
        this.source_account_id_long = source_account_id_long;
        this.destinationAccount = destinationAccount;
        this.destination_account_id_long = destination_account_id_long;
        this.category = category;
        this.description = description;
    }

    public Transaction(){}

    // Getters and Setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account source_account) {
        this.sourceAccount = source_account;
    }

    public Long getSource_account_id_long() {
        return source_account_id_long;
    }

    public void setSource_account_id_long(Long source_account_id_long) {
        this.source_account_id_long = source_account_id_long;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destination_account) {
        this.destinationAccount = destination_account;
    }

    public Long getDestination_account_id_long() {
        return destination_account_id_long;
    }

    public void setDestination_account_id_long(Long destination_account_id_long) {
        this.destination_account_id_long = destination_account_id_long;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
