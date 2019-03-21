package com.hub.daos;

import com.hub.models.HubEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HubEventRepository extends JpaRepository<HubEvent, Integer> {

    Iterable<HubEvent> findByDptID(Integer dptID);

    Iterable<HubEvent> findByFrID(Integer frID);
}
