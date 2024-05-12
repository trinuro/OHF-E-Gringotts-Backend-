package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Query("SELECT s FROM Transaction s WHERE s.sourceAccount.id = ?1 OR s.destinationAccount.id = ?1")
    List<Transaction> findAllByAccount_Id(long id);

    List<Transaction> findTransactionBySourceAccount_Id(long id);

    @Query("SELECT s FROM Transaction s WHERE s.dateTime BETWEEN ?1 AND ?2")
    List<Transaction> findTransactionsByDateTimeBetween(String startTime, String endTime);

    @Query("SELECT s FROM  Transaction s WHERE s.category=?1")
    List<Transaction> findTransactionByCategory(String categoryName);

    @Query("SELECT s FROM  Transaction s WHERE s.sourceAccount=?1")
    List<Transaction> findTransactionBySourceAccount(Account src);

    @Query("SELECT s FROM  Transaction s WHERE s.destinationAccount=?1")
    List<Transaction> findTransactionByDestinationAccount(Account src);
}
