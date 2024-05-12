package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import com.example.egringotts.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

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
    public List<Transaction> getAllTransactions(long id){
        List<Transaction> transactions =  transactionRepository.findAllByAccount_Id(id);
//        removeAccountInfo(transactions);
        return transactions;
    }

    /**
     * Add a new transaction to database
     * @param transaction Transaction to be recorded
     */
    public void addNewTransaction(Transaction transaction){
        transactionRepository.save(transaction);
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
        List<Transaction> transactionList = transactionRepository.findTransactionsByDateTimeBetween(startTime, endTime);
        removeAccountInfo(transactionList);
        return transactionList;
    }

    /**
     * Get a list of transaction in the days before a certain date
     * @param dateTime Date and time to end ("yyyy-MM-dd HH:mm:ss")
     * @param numberOfDays Number of days before specified date and time
     * @return List of transaction
     */
    public List<Transaction> getTransactionByDaysBefore(String dateTime, int numberOfDays){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dto = LocalDateTime.parse(dateTime, formatter);

        String endTime = dto.format(formatter);
        String startTime = dto.minusDays(numberOfDays).format(formatter);
        List<Transaction> transactions = transactionRepository.findTransactionsByDateTimeBetween(startTime, endTime);
        removeAccountInfo(transactions);
        return transactions;
    }

}
