package com.hub.daos;

import com.hub.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface LikeRepository extends JpaRepository<Like, String> {

    ArrayList<Like> findByContentID(Integer contentID);
}
