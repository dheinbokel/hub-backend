package com.hub.daos;

import com.hub.models.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

    Iterable<Content> findByContentTypeAndIsActive(String contentType, boolean isActive);

    Iterable<Content> findByIsActive(boolean isActive);

    Iterable<Content> findByIsFeatured(boolean isFeatured);

    Optional<Content> findByContentName(String contentName);
}
