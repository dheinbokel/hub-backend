package com.hub.daos;

import com.hub.models.HubUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<HubUser, Integer> {
}