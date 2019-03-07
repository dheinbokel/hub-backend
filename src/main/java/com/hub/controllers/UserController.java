package com.hub.controllers;

import com.hub.models.HubUser;
import com.hub.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody HubUser addUser(@RequestBody HubUser hubUser){

        hubUser.setPassword(bCryptPasswordEncoder.encode(hubUser.getPassword()));
        userService.addUser(hubUser);
        return hubUser;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getAllUsers(){
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<HubUser> getUserById(@PathVariable(value = "id")Integer userID){
        return userService.findUserById(userID);
    }
}
