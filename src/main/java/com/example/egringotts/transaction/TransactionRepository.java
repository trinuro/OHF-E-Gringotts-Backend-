package com.example.egringotts.transaction;

import com.example.egringotts.account.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Override
    List<Transaction> findAll();

    List<Transaction> findTransactionBySourceAccount_Id(long id);

    @Query("SELECT s FROM Transaction s WHERE s.dateTime BETWEEN ?1 AND ?2")
    Optional<Transaction> findTransactionByDate_time_stringAndDate_time_string(String startDate, String endDate);

    @Query("SELECT s FROM  Transaction s WHERE s.category=?1")
    Optional<Transaction> findTransactionByCategory(String categoryName);

    @Query("SELECT s FROM  Transaction s WHERE s.sourceAccount=?1")
    Optional<Transaction> findTransactionBySourceAccount(Account src);
}
