package com.example.egringotts.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Gets all users in the database
     * @return A list of all users of class User
     */
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserInfo(String method, String matchString){
        Optional<User> result = null;
        method = method.toLowerCase();

        switch(method){
            case "email":
                result = userRepository.findUserByEmail(matchString);
                break;
            case "name":
                result = userRepository.findUserByName(matchString);
                break;
        }

        if(result.isEmpty()){
            // if no such user exists
            throw new IllegalStateException(); // Don't tell the reason
        }

        User user = result.orElseThrow(
                ()->{throw new IllegalStateException();}
        );

        user.setPassword(""); // hide password

        return user;
    }

    /**
     * Adds a new user in "user" table
     * @param user User object
     */
    public void addNewUser(User user) {
        Optional<User> studentOptional = userRepository.findUserByEmail(user.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email is taken"); // if email is already registered, throw exception
        }
        userRepository.save(user);

    }
}
