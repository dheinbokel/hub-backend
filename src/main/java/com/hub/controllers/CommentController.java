package com.hub.controllers;

import com.hub.models.Comments;
import com.hub.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    private CommentService commentService;

    CommentController(CommentService commentService){

        this.commentService = commentService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Comments> getAllComments(){
        return commentService.findAllComments();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Comments> getCommentById(@PathVariable(value = "id") Integer commentID){
        return commentService.findCommentById(commentID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Comments addComment(@RequestBody Comments comments){
        commentService.addComment(comments);
        return comments;
    }
}
