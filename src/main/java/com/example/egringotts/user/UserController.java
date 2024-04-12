package com.example.egringotts.user;

import com.example.egringotts.user.Repositories.GoblinRepository;
import com.example.egringotts.user.Services.GoblinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final GoblinService goblinService;
    private final GoblinRepository goblinRepository;

    @Autowired
    public UserController(
            UserService userService, UserRepository userRepository,
            GoblinService goblinService, GoblinRepository goblinRepository
    ){
        this.userService = userService;
        this.userRepository = userRepository;
        this.goblinService = goblinService;
        this.goblinRepository = goblinRepository;
    }

    /**
     * API Endpoint to get all users
     * @return A list of JSON of users
     */
    @GetMapping(path="/all")
    public @ResponseBody List<User> getAllUsers() {
        // This returns a JSON or XML with the users
        return (List<User>) userService.getAllUsers();
    }

    /**
     * API Endpoint to get one user based on name or email
     * @param property Either "email" or "name"
     * @param value Either email address if property is email or username if property is name
     * @return JSON of user information
     */
    @GetMapping(path="/getUser")
    public @ResponseBody User getUserByProperty(@RequestParam(name="property") String property, @RequestParam(name="value") String value){
        return userService.getUserInfo(property, value);
    }

    /**
     * Adds a new user in "user" table
     * 0 for Goblin, 1 for Platinum Patronus, 2 for Golden Galleon, 3 for Silver Snitch
     */
    @PostMapping(path="/create")
    public void createNewUsers(@RequestBody User user, @RequestParam(name="type") String typeNumber){
        int type = -1;
        try{
            type = Integer.valueOf(typeNumber);
        }catch(NumberFormatException e){
            // If type variable supplied is not integer
            throw new IllegalStateException(e); // show user the error
        }


        switch(type){
            case 0:
                User<Goblin> newGoblin = new User<>(user, new Goblin());
                userService.addNewUser(newGoblin);
                break;
            case 1:
                User<PlatinumPatronus> newPatron = new User<>(user, new PlatinumPatronus());
                userService.addNewUser(newPatron);
                break;
            case 2:
                User<GoldenGalleon> newGall = new User<>(user, new GoldenGalleon());
                userService.addNewUser(newGall);
                break;
            case 3:
                User<SilverSnitch> newSnitch = new User<>(user, new SilverSnitch());
                userService.addNewUser(newSnitch);
                break;
            default:
                throw new IllegalStateException(
                        """
                        Illegal type of user detected.
                        Please select a value between 0 and 3.
                        0 for Goblin, 1 for Platinum Patronus, 2 for Golden Galleon, 3 for Silver Snitch
                        """
                );
        }

    }
}
