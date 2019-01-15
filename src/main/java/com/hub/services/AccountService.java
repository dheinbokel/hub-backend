package com.hub.services;

import com.hub.models.LoginCredentials;
import com.hub.models.MockToken;
import org.springframework.stereotype.Service;

/**
 * This class deploys Dao's that in turn will communicate with the database.  It will deploy Dao's that
 * when we require a service pertaining to a service for an account.
 * @author Doug Heinbokel on 1/14/19
 */
@Service
public class AccountService {

    /**
     * Usually would call active directory.  Normally, a service calls a Dao, however, since AD is itself
     * a kind of Dao, this service will communicate with AD itself.  For now, we have it return a Mock Token
     * to represent what we may need from AD.
     * @return
     */
    public MockToken login(LoginCredentials credentials){

        if(credentials != null) {
            return new MockToken("dheinbokel", "Doug",
                    "Heinbokel", "Back End", "Cool guy");
        }
        return null; //would actually need to return an error, but for now, it will just return null
    }

    /**
     * Logs out the user.  Finds and deletes the token from AD.  The front end will pass in the userName
     * of the user and search for the token based on that and deletes it to logout the user.
     * @param userName
     * @return
     */
    public String logout(String userName){

        //This method will also delete the token off the server searching for the token using the userName
        //being passed into it.  Since we don't have any way of using AD at the moment, we just return a success
        //message that will be returned in the form of a String.
        return "Logout Successful";
    }
}
