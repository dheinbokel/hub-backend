package com.hub.daos;

import com.hub.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    Iterable<Tag> findByIsActive(boolean isActive);
}
