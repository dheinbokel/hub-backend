package com.hub.daos;

import com.hub.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    Iterable<Notification> findByUserID(Integer userID);

    Iterable<Notification> findByUserIDAndIsActive(Integer userID, boolean isActive);
}
