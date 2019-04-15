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
    public @ResponseBody Iterable<Comments> getAllComments(@RequestParam(defaultValue = "true", required = false)
                                                           boolean active){
        return commentService.findAllCommentsByActiveStatus(active);
    }

    /**
     * This endpoint works similar to the /all endpoint, except, it returns an iterable of comments based on the contentID
     * that is sent in through a path variable.
     * @param contentID
     * @return
     */
    @RequestMapping(value = "/bycontent", method = RequestMethod.GET)
    public @ResponseBody Iterable<Comments> findCommentsByContentID(@RequestParam Integer contentID,
                                                                    @RequestParam(defaultValue = "true", required = false)
                                                                    boolean active){

        return commentService.findCommentsByContentID(contentID, active);
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
     * This endpoint returns all comments, as an iterable, that were made by a user with the path variable username.
     * @param userName
     * @return
     */
    @RequestMapping(value = "/all/username/{userName}", method = RequestMethod.GET)
    public @ResponseBody Iterable<Comments> findByUserName(@PathVariable(value = "userName") String userName){

        return commentService.findByUserName(userName);
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

    /**
     * This endpoint is for altering a comment object to change what is in the comment. Requires a Comments object with the
     * edited text and an Integer commentID.
     * @param comments Comments object
     * @param id Integer
     * @return the comment that was altered.
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    public @ResponseBody Comments editComment(@RequestBody Comments comments, @PathVariable(value = "id") Integer id){

        return commentService.changeComment(comments, id);
    }

    /**
     * This endpoint changes the active status of the comment from true to false and false to true depending on what the
     * current active status is. Requires and Integer commentID and returns the comment that was toggled.
     * @param id Integer
     * @return Comments object
     */
    @RequestMapping(value = "/toggle/{id}", method = RequestMethod.PUT)
    public @ResponseBody Integer toggleComment(@PathVariable(value = "id") Integer id){

        return commentService.toggleComment(id);
    }

    /**
     * This endpoint deletes a comment with the commentID matching the id sent in through the path variable. The id value
     * Integer is sent back.
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody Integer deleteComment(@PathVariable(value = "id") Integer id){

        return commentService.deleteComment(id);
    }
}
