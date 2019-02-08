package com.hub.daos;

import com.hub.models.Groups;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends CrudRepository<Groups, Integer> {
}