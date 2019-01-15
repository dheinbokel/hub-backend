package com.hub.controllers;

import com.hub.models.LoginCredentials;
import com.hub.models.MockToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.hub.services.AccountService;

/**
 * This class handles account actions such as logging in and out.  Takes in HTTP requests (such as
 * those that come from the Angular front end app) and returns a response to the requester.
 * @author Doug Heinbokel on 1/14/19
 */
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;

    /**
     * Simple constructor that instantiates an AccountService object.
     * @param accountService The {@link AccountService} to use.
     */
    AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    /*
    This is a method meant for only testing that the server is working and a connection is being made with the
    project.

    @RequestMapping(method = RequestMethod.GET)
    public MockToken getToken(){
        return new MockToken("dheinbokel", "Doug",
                "Heinbokel", "Back End", "Cool guy");
    }
    */

    /**
     * Called for the "/login" end point for POST method.  Uses form data to call the account servicer
     * and returns the result as JSON data to the front-end.
     * @param credentials The {@link LoginCredentials} to use.
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public MockToken login(@RequestBody LoginCredentials credentials){

        return this.accountService.login(credentials);
    }

    /**
     * Will request the userName of the user from the front-end and use it to send in to the service in order
     * to delete the token and log out the user.  As of right now, this method does not have that capability
     * to do this.  Validation will also be used here but we have no way of actually doing this presently, until
     * we get AD information.
     * @param userName
     * @return This is going to return a message that lets the user know that the logout has been successful or not.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(@RequestBody String userName){

        return this.accountService.logout(userName);
    }
}
