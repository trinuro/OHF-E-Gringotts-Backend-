package com.example.egringotts.account;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    @Override
    List<Account> findAll();

    // Select based on user_id foreign key
    @Query("SELECT s FROM Account s WHERE s.myUser.id=?1")
    Optional<Account> findAccountByMyUser_Id(long id);


    @Query("SELECT s FROM Account s WHERE s.id=?1")
    Optional<Account> findAccountById(long id);

    @Modifying(clearAutomatically = true) // persistence will be always updated
    @Query("UPDATE Account a SET a.knut_balance = ?1 WHERE a.myUser.id=?2")
    void updateKnutBalanceByMyUser_Id(double knutBalance, long id);
}
