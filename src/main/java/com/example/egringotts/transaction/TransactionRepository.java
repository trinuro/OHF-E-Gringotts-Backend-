package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Override
    List<Transaction> findAll();

    List<Transaction> findTransactionBySourceAccount_Id(long id);

    @Query("SELECT s FROM Transaction s WHERE s.dateTime BETWEEN ?1 AND ?2")
    List<Transaction> findTransactionsByDateTimeBetween(Date startTime, Date endTime);

    @Query("SELECT s FROM  Transaction s WHERE s.category=?1")
    List<Transaction> findTransactionByCategory(String categoryName);

    @Query("SELECT s FROM  Transaction s WHERE s.sourceAccount=?1")
    List<Transaction> findTransactionBySourceAccount(Account src);

    @Query("SELECT s FROM  Transaction s WHERE s.destinationAccount=?1")
    List<Transaction> findTransactionByDestinationAccount(Account src);
}
