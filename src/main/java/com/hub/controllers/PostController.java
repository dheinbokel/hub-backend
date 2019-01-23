package com.hub.controllers;

import com.hub.daos.PostRepository;
import com.hub.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    /*
    PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }*/


    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody String addPost(@RequestParam String text, @RequestParam String title,
                                        @RequestParam String file, @RequestParam String author){

        Post p = new Post();
        p.setText(text);
        p.setTitle(title);
        p.setFile(file);
        p.setAuthor(author);
        postRepository.save(p);
        return "Saved";
    }
}
