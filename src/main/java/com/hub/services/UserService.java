package com.hub.services;

import com.hub.RequestModels.NotificationRequest;
import com.hub.RequestModels.SubscriptionRequest;
import com.hub.daos.NotificationRepository;
import com.hub.daos.SubscriptionRepository;
import com.hub.daos.UsersRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

/**
 * This class is responsible for handling the logic and thinking behinds the user controller endpoint calls.
 */
@Service
public class UserService {

    private UsersRepository usersRepository;
    private SubscriptionRepository subscriptionRepository;
    private NotificationRepository notificationRepository;

    UserService(UsersRepository usersRepository, SubscriptionRepository subscriptionRepository, NotificationRepository notificationRepository){

        this.usersRepository = usersRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.notificationRepository = notificationRepository;
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

    public Iterable<Notification> findNotificationByUserID(Integer userID){

        return notificationRepository.findByUserID(userID);
    }

    public Iterable<Subscription> findSubByUserID(Integer userID){

        return subscriptionRepository.findByUserID(userID);
    }

    public Iterable<Subscription> findSubByTagID(Integer tagID){

        return subscriptionRepository.findByTagID(tagID);
    }

    public void addSub(SubscriptionRequest subscriptionRequest){

        Iterable<Subscription> subscriptions = subscriptionRepository.findByUserID(subscriptionRequest.getUserID());

        for(Subscription subscription : subscriptions){

            subscriptionRepository.deleteById(subscription.getSubID());
        }

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

    public void deleteNotificationsByID(NotificationRequest notificationRequest){

        Integer[] ids = notificationRequest.getNotificationIDs();

        for(Integer id : ids){

            notificationRepository.deleteById(id);
        }
    }

    public String deleteSubscriptionByID(String subID){

        subscriptionRepository.deleteById(subID);

        return subID;
    }

    public ArrayList<Notification> setNotificationInactive(NotificationRequest notificationRequest){

        Integer[] ids = notificationRequest.getNotificationIDs();
        ArrayList<Notification> notifications = new ArrayList<>();

        for(Integer id : ids){

            Notification notification = notificationRepository.findById(id)
                    .orElseThrow(() -> new HubNotFoundException("Could not find Notification for notificationID: " + id));

            notification.setActive(false);

            Notification updatedNotification = notificationRepository.save(notification);
            notifications.add(updatedNotification);
        }
        return notifications;
    }

    public HubUser editUser(HubUser hubUser, Integer id){

        HubUser user = usersRepository.findById(id)
                .orElseThrow(() -> new HubNotFoundException("Could not find user for userID: " + id));

        user.setUserName(hubUser.getUserName());
        user.setPassword(hubUser.getPassword());
        user.setEmail(hubUser.getEmail());
        user.setfName(hubUser.getfName());
        user.setlName(hubUser.getlName());
        user.setDptID(hubUser.getDptID());
        user.setFrID(hubUser.getFrID());
        user.setPrmID(hubUser.getPrmID());

        HubUser updatedUser = usersRepository.save(user);

        return updatedUser;
    }

    public Integer toggleUser(Integer userID){

        HubUser user = usersRepository.findById(userID)
                .orElseThrow(() -> new HubNotFoundException("Could not find user for userID: " + userID));

        if(user.isActive()){
            user.setActive(false);
        }
        else{
            user.setActive(true);
        }

        usersRepository.save(user);
        return userID;
    }
}
