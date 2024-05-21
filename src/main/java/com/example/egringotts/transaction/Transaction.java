package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name="transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double amount;
    @Column(name="date_time")
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateTime;
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
    @Column(name="source_currency")
    private String sourceCurrency;
    @Column(name="destination_currency")
    private String destinationCurrency;

    // constructors
    public Transaction(long id, double amount, Date dateTime, Account sourceAccount, Long source_account_id_long, Account destinationAccount, Long destination_account_id_long, String category, String description, String sourceCurrency, String destinationCurrency) {
        this.id = id;
        this.amount = amount;
        this.dateTime = dateTime;
        this.sourceAccount = sourceAccount;
        this.source_account_id_long = source_account_id_long;
        this.destinationAccount = destinationAccount;
        this.destination_account_id_long = destination_account_id_long;
        this.category = category;
        this.description = description;
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
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

    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDateTime() {
        return dateTime;
    }

    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public void setDateTime(Date dateTime) {
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

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(String destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }
}
