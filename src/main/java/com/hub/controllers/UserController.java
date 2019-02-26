package com.hub.controllers;

import com.hub.daos.UsersRepository;
import com.hub.models.HubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody HubUser addUser(@RequestBody HubUser hubUser){
        usersRepository.save(hubUser);
        return hubUser;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubUser> getAllUsers(){
        return usersRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<HubUser> getUserById(@PathVariable(value = "id")Integer userID){
        return usersRepository.findById(userID);
    }
}
