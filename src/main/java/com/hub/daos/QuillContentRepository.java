package com.hub.daos;

import com.hub.models.QuillContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuillContentRepository extends JpaRepository<QuillContent, Integer> {
}
