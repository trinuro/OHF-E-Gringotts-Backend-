package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import com.example.egringotts.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Transaction> getAllTransactions(){
        List<Transaction> transactions =  transactionRepository.findAll();
        ListIterator<Transaction> li = transactions.listIterator();
        while(li.hasNext()){
            Transaction tmp = li.next();
            tmp.setDestinationAccount(null);
            tmp.setSourceAccount(null); // Hide destination and source account info
        }
        return transactions;
    }

    /**
     * Add a new transaction to database
     * @param transaction Transaction to be recorded
     */
    public void addNewTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }

    public Transaction getTransactionBy(String method, String matchString){
        Optional<Transaction> transactionOptional;
        switch(method){
            case "category":
                transactionOptional= transactionRepository.findTransactionByCategory(matchString);
                break;
            case "source_account":
                Account src = accountRepository.findAccountById(Long.valueOf(matchString)).orElseThrow(
                        ()->{throw new IllegalStateException("No such source account");}
                );
                transactionOptional= transactionRepository.findTransactionBySourceAccount(src);
                break;
            default:
                throw new IllegalStateException("Illegal method name");
        }
        Transaction output = transactionOptional.orElseThrow(
                ()->{throw new IllegalStateException("No such transaction");}
        );
        return output;
    }

}
