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
     * @param hubUser
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody HubUser addUser(@RequestBody HubUser hubUser){

        hubUser.setPassword(bCryptPasswordEncoder.encode(hubUser.getPassword()));
        userService.addUser(hubUser);
        return hubUser;
    }

    /**
     * Returns all users in the database as an iterable of HubUsers.
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getAllUsers(){
        return userService.findAllUsers();
    }

    /**
     * Finds and returns a user by the id passed into as a path variable.
     * @param userID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<HubUser> getUserById(@PathVariable(value = "id")Integer userID){
        return userService.findUserById(userID);
    }

    /**
     * Finds and returns an iterable of HubUsers that have the department id sent in as a path variable.
     * @param dptID
     * @return
     */
    @RequestMapping(value = "/bydepartment/{dptID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getUsersByDepartmentID(@PathVariable(value = "dptID")Integer dptID){
        return userService.findUserByDptID(dptID);
    }

    /**
     * Finds and returns an iterable of HubUsers that have the franchise id sent in as a path variable.
     * @param frID
     * @return
     */
    @RequestMapping(value = "/byfranchise/{frID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getUsersByFranchiseID(@PathVariable(value = "frID")Integer frID){
        return userService.findUserByFrID(frID);
    }

    /**
     * Finds and returns an iterable of HubUsers that have the permission id sent in as a path variable.
     * @param prmID
     * @return
     */
    @RequestMapping(value = "/bypermission/{prmID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getUsersByPermissionID(@PathVariable(value = "prmID")Integer prmID){
        return userService.findUserByPrmID(prmID);
    }

    /**
     * Finds and returns a HubUser that has the userName sent in as a path variable.
     * @param userName
     * @return
     */
    @RequestMapping(value = "/byusername/{userName}", method = RequestMethod.GET)
    public @ResponseBody HubUser getUserByUserName(@PathVariable(value = "userName") String userName){
        return userService.findUserByUserName(userName);
    }

    @RequestMapping(value = "/subscription/byuserid/{userID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Subscription> getSubByUserID(@PathVariable(value = "userID") Integer userID){

        return userService.findSubByUserID(userID);
    }

    @RequestMapping(value = "/subscription/bytagid/{tagID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Subscription> getSubByTagID(@PathVariable(value = "tagID") Integer tagID){

        return userService.findSubByTagID(tagID);
    }

    @RequestMapping(value = "/subscription/add", method = RequestMethod.POST)
    public void subscribe(@RequestBody SubscriptionRequest subscriptionRequest){

        userService.addSub(subscriptionRequest);
    }

    /**
     * This endpoint finds and returns all notifications that share a userID with the userID in the path variable
     * @param userID Integer
     * @return Iterable of notification objects.
     */
    @RequestMapping(value = "/notifications/{userID}", method = RequestMethod.GET)
    public Iterable<Notification> getNotificationByUserID(@PathVariable(value = "userID") Integer userID){

        return userService.findNotificationByUserID(userID);
    }

    /**
     * This endpoint deletes all notifications with the ids given in the NotificationRequest's notificationsIDs field.
     * @param notificationRequest
     */
    @RequestMapping(value = "/notifications/remove", method = RequestMethod.DELETE)
    public void deleteNotificationsByUserID(@RequestBody NotificationRequest notificationRequest){

        userService.deleteNotificationsByID(notificationRequest);
    }

    /**
     * This endpoint requires a NotificationRequest object and sets all notifications sent in to inactive. Sends back an
     * ArrayList of notifications that were set to inactive.
     * @param notificationRequest
     * @return
     */
    @RequestMapping(value = "/notifications/switch", method = RequestMethod.PUT)
    public ArrayList<Notification> setNotificationsInactive(@RequestBody NotificationRequest notificationRequest){

        return userService.setNotificationInactive(notificationRequest);
    }
}
