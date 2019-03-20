package com.hub.controllers;

import com.hub.models.Comments;
import com.hub.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This class was created by Doug Heinbokel on 3/1/19.
 * This class is responsible for listening for calls to the back end that have to do with comments, whether to add or
 * find a comment or list of comments.
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    private CommentService commentService;

    CommentController(CommentService commentService){

        this.commentService = commentService;
    }

    /**
     * This endpoint is used to find all comments in the database.  It returns an iterable of comments in the response body
     * that is itself returned from the comment service.
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Comments> getAllComments(){
        return commentService.findAllComments();
    }

    /**
     * This endpoint works similar to the /all endpoint, except, it returns an iterable of comments based on the contentID
     * that is sent in through a path variable.
     * @param contentID
     * @return
     */
    @RequestMapping(value = "/all/content/{contentID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Comments> findCommentsByContentID(@PathVariable(value = "contentID") Integer contentID){

        return commentService.findCommentsByContentID(contentID);
    }

    /**
     * This endpoint is the almost the same as the /all/content/{contentID} endpoint. The difference is that a userID
     * is input instead and it returns an iterable of comments based on the userID input as a path variable.
     * @param userID
     * @return
     */
    @RequestMapping(value = "/all/user/{userID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Comments> findCommentsByUserID(@PathVariable(value = "userID") Integer userID){

        return commentService.findCommentsByUserID(userID);
    }

    /**
     * This endpoint returns a comment based on the commentID passed into the endpoint as a path variable.  May come back
     * as null if there is no comment with the id input.
     * @param commentID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Comments> getCommentById(@PathVariable(value = "id") Integer commentID){
        return commentService.findCommentById(commentID);
    }

    /**
     * This endpoint adds a new comment to the database and returns the created comment to the front end.
     * @param comments
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Comments addComment(@RequestBody Comments comments){
        commentService.addComment(comments);
        return comments;
    }
}
