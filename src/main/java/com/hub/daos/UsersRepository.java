package com.hub.daos;

import com.hub.models.HubUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<HubUser, Integer> {

    HubUser findByUserName(String username);
}