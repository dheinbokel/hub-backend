package com.hub.controllers;

import com.hub.daos.TagRepository;
import com.hub.models.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/tags")
public class TagController {

    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Tag> getAllTags(){
        return tagRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Tag getTagById(@PathVariable(value = "id") Integer tagID){
        return tagRepository.getOne(tagID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Tag addTag(@RequestBody Tag tag){
        tagRepository.save(tag);
        return tag;
    }
}
