package com.hub.daos;

import com.hub.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

    Iterable<Comments> findByContentIDAndIsActive(Integer contentID, boolean isActive);

    Iterable<Comments> findByUserID(Integer userID);

    Iterable<Comments> findByIsActive(boolean isActive);
}
