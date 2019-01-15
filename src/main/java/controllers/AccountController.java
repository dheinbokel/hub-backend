package controllers;

import models.LoginCredentials;
import models.MockToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import services.AccountService;

/**
 * This class handles account actions such as logging in and out.  Takes in HTTP requests (such as
 * those that come from the Angular front end app) and returns a response to the requester.
 * @author Doug Heinbokel on 1/14/19
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MockToken login(@RequestBody LoginCredentials credentials){

        return accountService.login(credentials);
    }
}
