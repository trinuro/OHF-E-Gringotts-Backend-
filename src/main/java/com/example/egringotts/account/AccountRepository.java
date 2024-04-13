package com.example.egringotts.account;

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
}
