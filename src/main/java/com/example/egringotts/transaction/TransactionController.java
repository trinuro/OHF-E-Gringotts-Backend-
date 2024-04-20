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

    // document
    @GetMapping(path="/getTransaction")
    public @ResponseBody Transaction getTransaction(@RequestParam(name="property") String property, @RequestParam(name="value") String value){
        return transactionService.getTransactionBy(property, value);
    }

    //document
    @GetMapping(path="/getTransactionByDateTime")
    public @ResponseBody List<Transaction> getTransactionByDateTime(@RequestParam(name="start") String startTime, @RequestParam(name="end") String endTime){
        return transactionService.getTransactionsByDateTime(startTime, endTime);
    }
}
