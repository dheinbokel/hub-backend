package com.hub.daos;

import com.hub.models.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, String> {

    Iterable<Subscription> findByUserID(Integer userID);

    Iterable<Subscription> findByTagID(Integer tagID);
}
