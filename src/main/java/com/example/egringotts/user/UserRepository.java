package com.example.egringotts.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();

    @Query("SELECT s from User s WHERE s.email=?1")
    Optional<User> findUserByEmail(String email);

    @Query("SELECT s from User s WHERE s.name=?1")
    Optional<User> findUserByName(String name);

}
