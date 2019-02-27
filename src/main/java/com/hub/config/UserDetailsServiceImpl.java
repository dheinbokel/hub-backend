package com.hub.config;

import com.hub.daos.UsersRepository;
import com.hub.models.HubUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UsersRepository usersRepository;

    UserDetailsServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{

        HubUser hubUser = usersRepository.findByUserName(userName);

        if(hubUser == null){
            throw new UsernameNotFoundException(userName);
        }
        return new User(hubUser.getUserName(), hubUser.getPassword(), new ArrayList<>());
    }
}
