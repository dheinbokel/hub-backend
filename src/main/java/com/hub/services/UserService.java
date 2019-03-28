package com.hub.services;

import com.hub.RequestModels.SubscriptionRequest;
import com.hub.daos.SubscriptionRepository;
import com.hub.daos.UsersRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.HubUser;
import com.hub.models.Subscription;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class is responsible for handling the logic and thinking behinds the user controller endpoint calls.
 */
@Service
public class UserService {

    private UsersRepository usersRepository;
    private SubscriptionRepository subscriptionRepository;

    UserService(UsersRepository usersRepository, SubscriptionRepository subscriptionRepository){

        this.usersRepository = usersRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    /**
     * Returns all the users in an iterable that come back from teh user repository findAll() method and sends it to
     * the controller.
     * @return
     */
    public Iterable<HubUser> findAllUsers(){

        return usersRepository.findAll();
    }

    /**
     * Adds a user to the database that was passed into the parameters.
     * @param hubUser
     * @return
     */
    public HubUser addUser(HubUser hubUser){

        usersRepository.save(hubUser);

        return hubUser;
    }

    /**
     * Finds a user with the userID passed into the parameters.  Will return a null value if the user does not exist and
     * throw a HubNotFoundException.
     * @param userID
     * @return
     */
    public Optional<HubUser> findUserById(Integer userID){

        Optional<HubUser> user = usersRepository.findById(userID);

        if(user.isPresent()){

            return user;
        }

        throw new HubNotFoundException("Could not find user for userID: " + userID);
    }

    /**
     * Finds and returns a list of users based on the department id sent into the parameter.
     * @param dptID
     * @return
     */
    public Iterable<HubUser> findUserByDptID(Integer dptID){

        return usersRepository.findByDptID(dptID);
    }

    /**
     * Finds a and returns list of users based on the franchise id sent into the parameter.
     * @param frID
     * @return
     */
    public Iterable<HubUser> findUserByFrID(Integer frID){

        return usersRepository.findByFrID(frID);
    }

    /**
     * Finds and returns a list of users based on the permission id sent into the parameter.
     * @param prmID
     * @return
     */
    public Iterable<HubUser> findUserByPrmID(Integer prmID){

        return usersRepository.findByPrmID(prmID);
    }

    /**
     * Finds and returns a user based on their userName that is sent into the parameter.
     * @param userName
     * @return
     */
    public HubUser findUserByUserName(String userName){

        return usersRepository.findByUserName(userName);
    }

    public Iterable<Subscription> findSubByUserID(Integer userID){

        return subscriptionRepository.findByUserID(userID);
    }

    public Iterable<Subscription> findSubByTagID(Integer tagID){

        return subscriptionRepository.findByTagID(tagID);
    }

    public void addSub(SubscriptionRequest subscriptionRequest){

        String user;

        if(subscriptionRequest.getUserID() >= 10){

            user = subscriptionRequest.getUserID().toString();
        }
        else{

            user = "0" + subscriptionRequest.getUserID().toString();
        }

        Integer[] subTags = subscriptionRequest.getTags();
        String tag;

        for(Integer subtag : subTags){

            if(subtag >= 10){

                tag = subtag.toString();
            }
            else{

                tag = "0" + subtag.toString();
            }

            String subID = user + tag;

            Subscription subscription = new Subscription(subID, subscriptionRequest.getUserID(), subtag);
            subscriptionRepository.save(subscription);
        }

    }
}
