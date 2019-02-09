package com.hub.daos;

import com.hub.models.HubEvent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HubEventRepository extends CrudRepository<HubEvent, Integer> {
}
