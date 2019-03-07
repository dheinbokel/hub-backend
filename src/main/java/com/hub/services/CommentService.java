package com.hub.services;

import com.hub.daos.CommentsRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.Comments;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    private CommentsRepository commentsRepository;

    CommentService(CommentsRepository commentsRepository){
        this.commentsRepository = commentsRepository;
    }

    public Iterable<Comments> findAllComments(){

        return commentsRepository.findAll();
    }

    public Optional<Comments> findCommentById(Integer commentID){

        Optional<Comments> comment = commentsRepository.findById(commentID);

        if(comment.isPresent()){

            return comment;
        }

        throw new HubNotFoundException("Could not find comment for commentID: " + commentID);
    }

    public Comments addComment(Comments comments){

        commentsRepository.save(comments);
        return comments;
    }
}
