package com.example.egringotts.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
    }

    /**
     * Get all transactions done by bank
     * @return List of transactions
     */
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    /**
     * Add a new transaction to database
     * @param transaction Transaction to be recorded
     */
    public void addNewTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }


}
