package services;

import models.LoginCredentials;
import models.MockToken;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    /**
     * Usually call active directory
     * @return
     */
    public MockToken login(LoginCredentials credentials){

        if(credentials != null) {
            return new MockToken("dheinbokel", "Doug",
                    "Heinbokel", "Back End", "Cool guy");
        }
        return null; //would actually need to return an error
    }
}
