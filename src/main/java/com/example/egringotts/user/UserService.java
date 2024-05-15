package com.example.egringotts.user;

import com.example.egringotts.utilities.Gmailer;
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

    /**
     * Get information about a particular user
     * Recommended to use this method as the password hash is censored in its output
     * @param method Either email or name
     * @param matchString The email of the user or the name of user (depends on method chosen)
     * @return User JSON
     */
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
    public <E extends AbstractUser> void addNewUser(User<E> user) {
        Optional<User> studentOptional = userRepository.findUserByEmail(user.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Email is taken"); // if email is already registered, throw exception
        }
        userRepository.save(user);
//        try{
//            String receiver = user.getEmail();
//            String subject = "E-Gringgots Registration Successful!";
//            String message = "You have successfully registered an account with us. Enjoy!";
//            Gmailer.sendEmail( receiver, subject, message);
//
//        }catch(Exception e) {
//            System.out.println(e);
//        }

    }

    /**
     * This method verifies whether the password and email combination exists in the database
     * @param input JSON of email address and password
     * @return User object
     */
    public User logInUser(User input){
        User user = userRepository.findUserByEmail(input.getEmail()).orElseThrow(
                ()->{throw new IllegalStateException(); }
        );
        if(user.getPassword().equals(input.getPassword())){
            return getUserInfo("email",input.getEmail());
        }else{
            throw new IllegalStateException(); // don't tell why log in failed
        }
    }

    /**
     * Method to get refresh token
     */
    public void refreshGmailToken(){
        Gmailer.getRefreshToken();
    }
}
