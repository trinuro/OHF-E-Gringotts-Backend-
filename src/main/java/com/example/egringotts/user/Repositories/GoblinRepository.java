package com.example.egringotts.user.Repositories;

import com.example.egringotts.user.Goblin;
import com.example.egringotts.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoblinRepository extends CrudRepository<User<Goblin>, Long> {

}
