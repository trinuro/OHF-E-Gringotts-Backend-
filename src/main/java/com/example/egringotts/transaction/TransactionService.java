package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import com.example.egringotts.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
//        removeAccountInfo(transactions);
        return transactions;
    }

    public List<Transaction> getAllTransactionsById(long accountId) {
        List<Transaction> transactions =  transactionRepository.findTransactionBySourceAccount_Id(accountId);
//        removeAccountInfo(transactions);
        return transactions;
    }

    public Transaction getLatestTransactionById(long accountId) {
        List <Transaction> transactions = transactionRepository.findLatestTransactionBySourceAccount_Id(accountId);
        return transactions.get(0);
    }

    /**
     * Add a new transaction to database
     * It will automatically decrease source currency in the source account and increase destination currency in the destination account
     * @param transaction Transaction to be recorded
     */
    public void addNewTransaction(Transaction transaction){

        switch(transaction.getSourceCurrency()){
            case "galleon":
                decreaseGalleonBalance(transaction.getSource_account_id_long(), transaction.getAmount());
                break;
            case "knut":
                decreaseKnutBalance(transaction.getSource_account_id_long(), transaction.getAmount());
                break;
            case "sickle":
                decreaseSickleBalance(transaction.getSource_account_id_long(), transaction.getAmount());
                break;
        } // decrease amount of source account (Also checks whether there is sufficient balance)

        switch(transaction.getDestinationCurrency()){
            case "galleon":
                increaseGalleonBalance(transaction.getDestination_account_id_long(), transaction.getAmount());
                break;
            case "knut":
                increaseKnutBalance(transaction.getDestination_account_id_long(), transaction.getAmount());
                break;
            case "sickle":
                increaseSickleBalance(transaction.getDestination_account_id_long(), transaction.getAmount());
                break;
        } // increase amount of destination account

        transactionRepository.save(transaction);
    }

    public void convertCurrency(Transaction transaction, double sourceAmount) {
        switch(transaction.getSourceCurrency()){
            case "galleon":
                decreaseGalleonBalance(transaction.getSource_account_id_long(), sourceAmount);
                break;
            case "knut":
                decreaseKnutBalance(transaction.getSource_account_id_long(), sourceAmount);
                break;
            case "sickle":
                decreaseSickleBalance(transaction.getSource_account_id_long(), sourceAmount);
                break;
        } // decrease amount of source account (Also checks whether there is sufficient balance)

        switch(transaction.getDestinationCurrency()){
            case "galleon":
                increaseGalleonBalance(transaction.getDestination_account_id_long(), transaction.getAmount());
                break;
            case "knut":
                increaseKnutBalance(transaction.getDestination_account_id_long(), transaction.getAmount());
                break;
            case "sickle":
                increaseSickleBalance(transaction.getDestination_account_id_long(), transaction.getAmount());
                break;
        } // increase amount of destination account
        transactionRepository.save(transaction);
    }

    /**
     * Get a list of transactions where method = matchstring
     *
     * @param id
     * @param method      Either "category", "source_account" or "destination_account"
     * @param matchString String to match
     * @return A list of transaction that fits criteria
     */
    public List<Transaction> getTransactionBy(long id, String method, String matchString){
        List<Transaction> transactions;
        switch(method){
            case "category":
                transactions= transactionRepository.findTransactionByCategory(matchString, id);
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
//        removeAccountInfo(transactions);
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
    public List<Transaction> getTransactionsByDateTime(String startTime, String endTime, long id){
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

        List<Transaction> transactionList = transactionRepository.findTransactionsByIdDateTimeBetween(startDateTime, endDateTime, id);
//        removeAccountInfo(transactionList);
        return transactionList;
    }

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
//        removeAccountInfo(transactionList);
        return transactionList;
    }

    /**
     * Get a list of transaction in the days before a certain date
     * @param dateTime Date and time to end ("yyyy-MM-dd HH:mm:ss")
     * @param numberOfDays Number of days before specified date and time
     * @return List of transaction
     */
    public List<Transaction> getTransactionByDaysBefore(String dateTime, long numberOfDays, long id){
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
        List<Transaction> transactions = transactionRepository.findTransactionsByIdDateTimeBetween(startDateTime, endDateTime, id);
        removeAccountInfo(transactions);
        return transactions;
    }

    /**
     * Method to increase the knut balance by "amount"
     * @param id Id of account to be changed
     * @param amount Amount of money to be increased
     */
    public void increaseKnutBalance(long id, Double amount){
        if(amount<0) throw new RuntimeException("Invalid amount");
        Account check = accountRepository.findAccountById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));

        Account currAccount = accountRepository.getReferenceById(id);
        currAccount.setKnut_balance(currAccount.getKnut_balance()+amount);
        accountRepository.save(currAccount);
    }

    /**
     * Method to decrease the knut balance by "amount"
     * @param id Id of account to be changed
     * @param amount Amount of money to be decreased
     */
    public void decreaseKnutBalance(long id, Double amount){
        if(amount<0) throw new RuntimeException("Invalid amount"); // check for negative amount
        Account check = accountRepository.findAccountById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));
        if(check.getKnut_balance()<=0) throw new RuntimeException("Insufficient balance");

        Account currAccount = accountRepository.getReferenceById(id);
        currAccount.setKnut_balance(currAccount.getKnut_balance()-amount);
        accountRepository.save(currAccount);
    }

    /**
     * Method to increase the galleon balance by "amount"
     * @param id Id of account to be changed
     * @param amount Amount of money to be increased
     */
    public void increaseGalleonBalance(long id, Double amount){
        if(amount<0) throw new RuntimeException("Invalid amount");
        Account check = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));

        Account currAccount = accountRepository.getReferenceById(id);
        currAccount.setGalleon_balance(currAccount.getGalleon_balance()+amount);
        accountRepository.save(currAccount);
    }

    /**
     * Method to decrease the galleon balance by "amount"
     * @param id Id of account to be changed
     * @param amount Amount of money to be decreased
     */
    public void decreaseGalleonBalance(long id, Double amount){
        if(amount<0) throw new RuntimeException("Invalid amount");
        Account check = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));
        if(check.getGalleon_balance()<=0) throw new RuntimeException("Insufficient balance");


        Account currAccount = accountRepository.getReferenceById(id);
        currAccount.setGalleon_balance(currAccount.getGalleon_balance()-amount);
        accountRepository.save(currAccount);
    }

    /**
     * Method to increase the sickle balance by "amount"
     * @param id Id of account to be changed
     * @param amount Amount of money to be increased
     */
    public void increaseSickleBalance(long id, Double amount){
        if(amount<0) throw new RuntimeException("Invalid amount");
        Account check = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));

        Account currAccount = accountRepository.getReferenceById(id);
        currAccount.setSickle_balance(currAccount.getSickle_balance()+amount);
        accountRepository.save(currAccount);
    }

    /**
     * Method to decrease the sickle balance by "amount"
     * @param id Id of account to be changed
     * @param amount Amount of money to be decreased
     */
    public void decreaseSickleBalance(long id, Double amount){
        if(amount<0) throw new RuntimeException("Invalid amount");
        Account check = accountRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Account not found"));
        if(check.getSickle_balance()<=0) throw new RuntimeException("Insufficient balance");

        Account currAccount = accountRepository.getReferenceById(id);
        currAccount.setSickle_balance(currAccount.getSickle_balance()-amount);
        accountRepository.save(currAccount);
    }

}
