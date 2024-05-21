package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import com.example.egringotts.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Get all transactions done by bank
     * @return List of transactions
     */
    public List<Transaction> getAllTransactions(){
        List<Transaction> transactions =  transactionRepository.findAll();
        removeAccountInfo(transactions);
        return transactions;
    }

    /**
     * Add a new transaction to database
     * @param transaction Transaction to be recorded
     */
    public void addNewTransaction(Transaction transaction){
        transactionRepository.save(transaction);
        switch(transaction.getSourceCurrency()){
            case "galleon":
                updateGalleonBalance(transaction.getSource_account_id_long(), transaction.getAmount()*-1);
                break;
            case "knut":
                updateKnutBalance(transaction.getSource_account_id_long(), transaction.getAmount()*-1);
                break;
            case "sickle":
                updateSickleBalance(transaction.getSource_account_id_long(), transaction.getAmount()*-1);
                break;
        } // decrease amount of source account

        switch(transaction.getDestinationCurrency()){
            case "galleon":
                updateGalleonBalance(transaction.getDestination_account_id_long(), transaction.getAmount());
                break;
            case "knut":
                updateKnutBalance(transaction.getDestination_account_id_long(), transaction.getAmount());
                break;
            case "sickle":
                updateSickleBalance(transaction.getDestination_account_id_long(), transaction.getAmount());
                break;
        } // increase amount of destination account
    }

    /**
     * Get a list of transactions where method = matchstring
     * @param method Either "category", "source_account" or "destination_account"
     * @param matchString String to match
     * @return A list of transaction that fits criteria
     */
    public List<Transaction> getTransactionBy(String method, String matchString){
        List<Transaction> transactions;
        switch(method){
            case "category":
                transactions= transactionRepository.findTransactionByCategory(matchString);
                break;
            case "source_account":
                Account src = accountRepository.findAccountById(Long.valueOf(matchString)).orElseThrow(
                        ()->{throw new IllegalStateException("No such source account");}
                );
                transactions= transactionRepository.findTransactionBySourceAccount(src);
                break;
            case "destination_account":
                // find account
                Account destination = accountRepository.findAccountById(Long.valueOf(matchString)).orElseThrow(
                        ()->{throw new IllegalStateException("No such source account");}
                );
                // find transactions related to that account
                transactions = transactionRepository.findTransactionByDestinationAccount(destination);
                break;
            default:
                throw new IllegalStateException("Illegal method name");
        }
        removeAccountInfo(transactions);
        return transactions;
    }

    /**
     * Remove all account information from a list of transactions
     * @param transactions List of transactions to remove account info
     */
    public void removeAccountInfo(List<Transaction> transactions){
        Iterator<Transaction> li = transactions.iterator();
        Transaction current;
        while(li.hasNext()){
            current = li.next();
            current.setDestinationAccount(null);
            current.setSourceAccount(null);
        }
    }

    /**
     * Get a list of transactions by date time
     * @param startTime Date and time to start (yyyy-MM-dd HH:mm:ss)
     * @param endTime Date and time to end (yyyy-MM-dd HH:mm:ss)
     * @return List of transactions that fit criteria
     */
    public List<Transaction> getTransactionsByDateTime(String startTime, String endTime){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDateTime = null;
        Date endDateTime = null;
        try{
            // convert string to datetime format
            startDateTime = fmt.parse(startTime);
            endDateTime = fmt.parse(endTime);
        }catch (java.text.ParseException e){
            throw new RuntimeException(e);
        }

        List<Transaction> transactionList = transactionRepository.findTransactionsByDateTimeBetween(startDateTime, endDateTime);
        removeAccountInfo(transactionList);
        return transactionList;
    }

    /**
     * Get a list of transaction in the days before a certain date
     * @param dateTime Date and time to end ("yyyy-MM-dd HH:mm:ss")
     * @param numberOfDays Number of days before specified date and time
     * @return List of transaction
     */
    public List<Transaction> getTransactionByDaysBefore(String dateTime, long numberOfDays){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dto = LocalDateTime.parse(dateTime, formatter);

        String endTime = dto.format(formatter);
        String startTime = dto.minusDays(numberOfDays).format(formatter); // minus number of days to get starting day
//        System.out.println(endTime+" "+startTime);

        Date startDateTime = null;
        Date endDateTime = null;
        try{
            // convert string to datetime format
            startDateTime = fmt.parse(startTime);
            endDateTime = fmt.parse(endTime);
        }catch (java.text.ParseException e){
            throw new RuntimeException(e);
        }
//        System.out.println(startDateTime+" "+endDateTime);
        List<Transaction> transactions = transactionRepository.findTransactionsByDateTimeBetween(startDateTime, endDateTime);
        removeAccountInfo(transactions);
        return transactions;
    }


    public void updateKnutBalance(long id, Double amount){
        Account currAccount = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount != null) {
            currAccount.setKnut_balance(currAccount.getKnut_balance()+amount);
        }
    }

    public void updateGalleonBalance(long id, Double amount){
        Account currAccount = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));
        System.out.println(currAccount.getGalleon_balance()+amount);
        if (amount != null) {
            currAccount.setGalleon_balance(currAccount.getGalleon_balance()+amount);
        }
    }

    public void updateSickleBalance(long id, Double amount){
        Account currAccount = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));

        if (amount != null) {
            currAccount.setSickle_balance(currAccount.getSickle_balance()+amount);
        }
    }

}
