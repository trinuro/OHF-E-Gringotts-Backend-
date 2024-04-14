package com.example.egringotts.transaction;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Override
    List<Transaction> findAll();

    List<Transaction> findTransactionBySourceAccount_Id(long id);
}
