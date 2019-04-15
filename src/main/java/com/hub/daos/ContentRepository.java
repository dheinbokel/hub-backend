package com.hub.daos;

import com.hub.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    Iterable<Content> findByContentType(String contentType);

    Iterable<Content> findByIsActive(boolean isActive);
}
