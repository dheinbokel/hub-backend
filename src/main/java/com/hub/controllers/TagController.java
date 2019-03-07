package com.hub.controllers;

import com.hub.models.Tag;
import com.hub.services.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/tags")
public class TagController {

    private TagService tagService;

    TagController(TagService tagService){

        this.tagService = tagService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Tag> getAllTags(){
        return tagService.findAllTags();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Tag> getTagById(@PathVariable(value = "id") Integer tagID){
        return tagService.findTagById(tagID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Tag addTag(@RequestBody Tag tag){
        tagService.addTag(tag);
        return tag;
    }
}
