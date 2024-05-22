package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Override
    List<Transaction> findAll();

    @Query("SELECT s FROM Transaction s WHERE s.sourceAccount.id = ?1 OR s.destinationAccount.id = ?1")
    List<Transaction> findTransactionBySourceAccount_Id(long id);

    @Query("SELECT s FROM Transaction s WHERE (s.dateTime BETWEEN ?1 AND ?2) AND (s.sourceAccount.id = ?3 OR s.destinationAccount.id = ?3)")
    List<Transaction> findTransactionsByDateTimeBetween(Date startTime, Date endTime, long id);

    @Query("SELECT s FROM  Transaction s WHERE s.category=?1 AND (s.sourceAccount.id = ?2 OR s.destinationAccount.id = ?2)")
    List<Transaction> findTransactionByCategory(String categoryName, long id);

    @Query("SELECT s FROM  Transaction s WHERE s.sourceAccount=?1")
    List<Transaction> findTransactionBySourceAccount(Account src);

    @Query("SELECT s FROM  Transaction s WHERE s.destinationAccount=?1")
    List<Transaction> findTransactionByDestinationAccount(Account src);
}
