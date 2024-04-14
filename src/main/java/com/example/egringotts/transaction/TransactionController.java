package com.example.egringotts.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/transaction")
public class TransactionController {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    @Autowired
    public TransactionController(TransactionRepository transactionRepository, TransactionService transactionService){
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
    }

    @GetMapping(path="/all")
    public @ResponseBody List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    @PostMapping(path="/create")
    public void createNewTransactions(@RequestBody Transaction transaction){
        transactionService.addNewTransaction(transaction);
    }
}