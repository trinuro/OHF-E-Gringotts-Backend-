package com.example.egringotts.user.Services;

import com.example.egringotts.user.Goblin;
import com.example.egringotts.user.User;
import com.example.egringotts.user.Repositories.GoblinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.egringotts.user.UserRepository;

import java.util.Optional;

@Service
public class GoblinService {
    private final GoblinRepository goblinRepository;
    private final UserRepository userRepository;

    @Autowired
    public GoblinService(GoblinRepository goblinRepository, UserRepository userRepository){
        this.goblinRepository = goblinRepository;
        this.userRepository = userRepository;
    }

//    public void addNewUser(User<Goblin> user) {
//        Optional<User> studentOptional = userRepository.findUserByEmail(user.getEmail());
//        if(studentOptional.isPresent()){
//            throw new IllegalStateException("Email is taken"); // if email is already registered, throw exception
//        }
//        goblinRepository.save(user);
//    }
//
}
