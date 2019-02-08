package com.hub.daos;

import com.hub.models.Comments;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends CrudRepository<Comments, Integer> {
}
