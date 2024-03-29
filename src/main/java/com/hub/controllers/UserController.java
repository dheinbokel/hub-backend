package com.hub.controllers;

import com.hub.RequestModels.NotificationRequest;
import com.hub.RequestModels.SubscriptionRequest;
import com.hub.models.HubUser;
import com.hub.models.Notification;
import com.hub.models.Subscription;
import com.hub.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * This class was created by Doug Heinbokel on 2/1/19.
 * This class listens for calls from the front end and responds based on which endpoint is called.
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){

        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Adds a user to the database and returns it to the front end without the password field. Requires all fields be
     * sent in as a HubUser.  When the user comes in, the password is encrypted for password security.
     * @param hubUser HubUser object
     * @return HubUser object
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody HubUser addUser(@RequestBody HubUser hubUser){

        hubUser.setPassword(bCryptPasswordEncoder.encode(hubUser.getPassword()));
        userService.addUser(hubUser);
        return hubUser;
    }

    /**
     * This endpoint is used to edit a user and submit new data for this user to the database.
     * @param hubUser HubUser object
     * @param id Integer
     * @return HubUser
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public @ResponseBody HubUser editUser(@RequestBody HubUser hubUser, @PathVariable(value = "id") Integer id){

        hubUser.setPassword(bCryptPasswordEncoder.encode(hubUser.getPassword()));
        return userService.editUser(hubUser, id);
    }

    /**
     * Switches the active status of a user from active to inactive and back with each call of the endpoint.
     * @param userID Integer
     * @return The Integer ID of the user that was toggled.
     */
    @RequestMapping(value = "/toggle/{userID}", method = RequestMethod.PUT)
    public @ResponseBody Integer toggleUser(@PathVariable(value = "userID")Integer userID){

        return userService.toggleUser(userID);
    }

    /**
     * Returns all users in the database as an iterable of HubUsers.
     * @return Iterable of HubUser objects
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getAllUsers(){
        return userService.findAllUsers();
    }

    /**
     * Finds and returns a user by the id passed into as a path variable.
     * @param userID Integer
     * @return Optional HubUser
     */
    @RequestMapping(value = "/byID/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<HubUser> getUserById(@PathVariable(value = "id")Integer userID){
        return userService.findUserById(userID);
    }

    /**
     * Finds and returns an iterable of HubUsers that have the department id sent in as a path variable.
     * @param dptID Integer
     * @return Iterable of HubUser objects
     */
    @RequestMapping(value = "/bydepartment/{dptID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getUsersByDepartmentID(@PathVariable(value = "dptID")Integer dptID){
        return userService.findUserByDptID(dptID);
    }

    /**
     * Finds and returns an iterable of HubUsers that have the franchise id sent in as a path variable.
     * @param frID Integer
     * @return Iterable of HubUser objects
     */
    @RequestMapping(value = "/byfranchise/{frID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getUsersByFranchiseID(@PathVariable(value = "frID")Integer frID){
        return userService.findUserByFrID(frID);
    }

    /**
     * Finds and returns an iterable of HubUsers that have the permission id sent in as a path variable.
     * @param prmID Integer
     * @return Iterable of HubUser objects
     */
    @RequestMapping(value = "/bypermission/{prmID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getUsersByPermissionID(@PathVariable(value = "prmID")Integer prmID){
        return userService.findUserByPrmID(prmID);
    }

    /**
     * Finds and returns a HubUser that has the userName sent in as a path variable.
     * @param userName String userName of user
     * @return HubUser
     */
    @RequestMapping(value = "/byusername/{userName}", method = RequestMethod.GET)
    public @ResponseBody HubUser getUserByUserName(@PathVariable(value = "userName") String userName){
        return userService.findUserByUserName(userName);
    }

    /**
     * This endpoint grabs all of the subscriptions in the database that share a userID with the ID sent in through the
     * path variable.
     * @param userID Integer
     * @return an Iterable list of subscriptions
     */
    @RequestMapping(value = "/subscription/byuserid/{userID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Subscription> getSubByUserID(@PathVariable(value = "userID") Integer userID){

        return userService.findSubByUserID(userID);
    }

    /**
     * This endpoint grabs all of the subscriptions from the database that share a tagID with the ID sent in through the
     * path variable
     * @param tagID Integer
     * @return an Iterable list of subscriptions
     */
    @RequestMapping(value = "/subscription/bytagid/{tagID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Subscription> getSubByTagID(@PathVariable(value = "tagID") Integer tagID){

        return userService.findSubByTagID(tagID);
    }

    /**
     * This endpoint is used for adding a subscription to a certain tag. It takes in a SubscriptionRequest object.
     * @param subscriptionRequest object
     */
    @RequestMapping(value = "/subscription/add", method = RequestMethod.POST)
    public void subscribe(@RequestBody SubscriptionRequest subscriptionRequest){

        userService.addSub(subscriptionRequest);
    }

    /**
     * Removes a single subscription by its id
     * @param subID String
     * @return String ID of the subscription that was deleted
     */
    @RequestMapping(value = "/subscription/remove/{subID}", method = RequestMethod.DELETE)
    public String deleteSub(@PathVariable(value = "subID") String subID){

        return userService.deleteSubscriptionByID(subID);
    }

    /**
     * This endpoint finds and returns all notifications that share a userID with the userID in the path variable. It also
     * filters based on whether the notification is active or not.
     * @param userID Integer
     * @return Iterable of notification objects.
     */
    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public Iterable<Notification> getNotificationByUserID(@RequestParam(defaultValue = "true", required = false) boolean active,
                                                          @RequestParam Integer userID) {

        return userService.findActiveNotificationByUserID(userID, active);
    }

    /**
     * This endpoint returns all notifications for a user with the same userID as what was sent in as a path variable.
     * @param userID Integer
     * @return Iterable list of notification objects
     */
    @RequestMapping(value = "/notifications/{userID}", method = RequestMethod.GET)
    public Iterable<Notification> getByJustID(@PathVariable(value = "userID") Integer userID){

        return userService.findByUserID(userID);
    }

    /**
     * This endpoint deletes a notification with the id given in the path variable.
     * @param notificationID Integer ID of the notification
     * @return void
     */
    @RequestMapping(value = "/notifications/remove/{notificationID}", method = RequestMethod.DELETE)
    public void deleteNotificationsByID(@PathVariable(value = "notificationID") Integer notificationID){

        userService.deleteNotificationsByID(notificationID);
    }

    /**
     * This endpoint deletes all notifications with the ids given in the NotificationRequest's notificationsIDs field.
     * @param idList String comma seperated list of notificationID's
     * @return void
     */
    @RequestMapping(value = "/notifications/remove", method = RequestMethod.DELETE)
    public void deleteNotificationsByAllIDs(@RequestParam String idList){

        userService.deleteNotificationsByAllIDs(idList);
    }

    /**
     * This endpoint requires a NotificationRequest object and sets all notifications sent in to inactive. Sends back an
     * ArrayList of notifications that were set to inactive.
     * @param notificationRequest object
     * @return ArrayList<Notification>
     */
    @RequestMapping(value = "/notifications/switch", method = RequestMethod.PUT)
    public ArrayList<Notification> setNotificationsInactive(@RequestBody NotificationRequest notificationRequest){

        return userService.setNotificationInactive(notificationRequest);
    }
}
