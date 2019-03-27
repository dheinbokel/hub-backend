package com.hub.daos;

import com.hub.models.ContentTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentTagRepository extends JpaRepository<ContentTag, String> {

    Iterable<ContentTag> findByContentID(Integer contentID);

    Iterable<ContentTag> findByTagID(Integer tagID);
}
