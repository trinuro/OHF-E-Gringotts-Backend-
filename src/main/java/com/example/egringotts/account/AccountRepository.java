package com.example.egringotts.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Override
    List<Account> findAll();

    // Select based on user_id foreign key
    @Query("SELECT s FROM Account s WHERE s.myUser.id=?1")
    Optional<Account> findAccountByMyUser_Id(long id);


    @Query("SELECT s FROM Account s WHERE s.id=?1")
    Optional<Account> findAccountById(long id);

    @Modifying (clearAutomatically = true)
    @Transactional
    @Query("UPDATE Account a SET a.knut_balance = ?1 WHERE a.myUser.id=?2")
    void updateKnutBalanceByMyUser_Id(double knutBalance, long id);

    @Modifying(clearAutomatically = true) // persistence will be always updated
    @Transactional
    @Query("UPDATE Account a SET a.galleon_balance = ?2 WHERE a.myUser.id=?1")
    void updateGalleonBalanceByMyUser_Id(long id, double galleonBalance);

    @Modifying(clearAutomatically = true) // persistence will be always updated
    @Transactional
    @Query("UPDATE Account a SET a.sickle_balance = ?2 WHERE a.myUser.id=?1")
    void updateSickleBalanceByMyUser_Id(long id, double sickleBalance);


}
