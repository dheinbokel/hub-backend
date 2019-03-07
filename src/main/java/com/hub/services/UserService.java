package com.hub.services;

import com.hub.daos.UsersRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.HubUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UsersRepository usersRepository;

    UserService(UsersRepository usersRepository){

        this.usersRepository = usersRepository;
    }

    public Iterable<HubUser> findAllUsers(){

        return usersRepository.findAll();
    }

    public HubUser addUser(HubUser hubUser){

        usersRepository.save(hubUser);

        return hubUser;
    }

    public Optional<HubUser> findUserById(Integer userID){

        Optional<HubUser> user = usersRepository.findById(userID);

        if(user.isPresent()){

            return user;
        }

        throw new HubNotFoundException("Could not find user for userID: " + userID);
    }
}
