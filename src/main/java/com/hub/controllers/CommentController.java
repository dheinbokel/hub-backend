package com.hub.controllers;

import com.hub.daos.CommentsRepository;
import com.hub.models.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired
    private CommentsRepository commentsRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Comments> getAllComments(){
        return commentsRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Comments getCommentById(@PathVariable(value = "id") Integer commentID){
        return commentsRepository.getOne(commentID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Comments addComment(@RequestBody Comments comments){
        commentsRepository.save(comments);
        return comments;
    }
}
