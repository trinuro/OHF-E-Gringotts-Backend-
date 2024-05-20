package com.example.egringotts.user_account;

import com.example.egringotts.user.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, UserAccountKey> {

    @Query("SELECT s FROM UserAccount s WHERE s.id.userId=?1")
    List<UserAccount> findFavouritesById(long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM UserAccount s WHERE s.id.userId=?1 AND s.id.accountId=?2")
    public void deleteFavourite(long userId, long accountId);
}
