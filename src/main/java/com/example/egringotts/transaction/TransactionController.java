package com.example.egringotts.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    /**
     * Endpoint to get all transactions in database
     * @return A list of transactions
     */
    @GetMapping(path="/all")
    public @ResponseBody List<Transaction> getAllTransactions(){
        return transactionService.getAllTransactions();
    }

    /**
     * Endpoint that creates a new transaction
     * @param transaction Transaction JSON
     * {
     *   "amount": 200,
     *   "dateTime": "2024-15-05 14:30:00",
     *   "source_account_id_long": 2,
     *   "destination_account_id_long": 2,
     *   "category" : "Entertainment",
     *   "description": "I love to watch movies!",
     *   "sourceCurrency": "galleon",
     *   "destinationCurrency": "knut"
     * }
     * currency type can only be "galleon", "knut" or "sickle"
     */
    @PostMapping(path="/create")
    public void createNewTransactions(@RequestBody Transaction transaction){
        transactionService.addNewTransaction(transaction);
    }

    /**
     * Endpoint that gets transaction by property (Either "category", "destination_account" or "source_account" only)
     * @param property Either "category", "destination_account" or "source_account" only
     * @param value Value to query database
     * @return A list of JSON
     */
    @GetMapping(path="/getTransaction")
    public @ResponseBody List<Transaction> getTransaction(@RequestParam(name="property") String property, @RequestParam(name="value") String value){
        return transactionService.getTransactionBy(property, value);
    }

    /**
     * Get transaction in a certain period of time
     *
     * @param startTime Starting date time (yyyy-MM-dd HH:mm:ss)
     * @param endTime Ending date time (yyyy-MM-dd HH:mm:ss)
     * @return A list of transactions JSONs
     */
    @GetMapping(path="/getTransactionByDateTime")
    public @ResponseBody List<Transaction> getTransactionByDateTime(
            @RequestParam(name="start")  String startTime,
            @RequestParam(name="end") String endTime){
        return transactionService.getTransactionsByDateTime(startTime, endTime);
    }

    /**
     * Get all transactions in the days before a certain date
     * @param endTime Ending date time (yyyy-MM-dd HH:mm:ss)
     * @param numberOfDays Number of days before ending date time
     * @return A list of transactions that fits criteria
     */
    @GetMapping(path="/getTransactionByDayBeforeDate")
    public @ResponseBody List<Transaction> getTransactionByDaysBeforeDate(@RequestParam(name="endDateTime") String endTime, @RequestParam(name="days") String numberOfDays){
            return transactionService.getTransactionByDaysBefore(endTime, Integer.parseInt(numberOfDays));
    }
}
