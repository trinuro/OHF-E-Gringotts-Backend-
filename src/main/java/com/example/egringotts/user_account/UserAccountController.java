package com.example.egringotts.user_account;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/v1/favourites")
public class UserAccountController {
    private UserAccountService userAccountService;
    private UserAccountRepository userAccountRepository;

    public UserAccountController(UserAccountService userAccountService, UserAccountRepository userAccountRepository) {
        this.userAccountService = userAccountService;
        this.userAccountRepository = userAccountRepository;
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<UserAccount> getAllFavourites(@RequestParam(name="id") long id){
        return userAccountService.getFavouritesById(id);
    }

    @PostMapping(path = "/create")
    public void addNewFavourites(@RequestBody Map<String, Long> data){
        long userId = data.get("userId");
        long accountId = data.get("accountId");

        userAccountService.addNewFavourites(userId, accountId);
    }

    @DeleteMapping(path ="/delete")
    public void deleteFavourite(@RequestParam(name="userId") long userId, @RequestParam(name = "accountId") long accountId){
        userAccountService.deleteFavourite(userId, accountId);
    }

}
