package com.hub.controllers;

import com.hub.models.Tag;
import com.hub.services.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/tags")
public class TagController {

    private TagService tagService;

    TagController(TagService tagService){

        this.tagService = tagService;
    }

    /**
     * This endpoint returns all tags with the active status passed in with the parameter (true = active, false = inactive).
     * If no parameter is given, the endpoint defaults to true.
     * @param active boolean
     * @return Iterable of tag objects
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Tag> getAllTags(@RequestParam(defaultValue = "true", required = false) boolean active){
        return tagService.findAllTagsBYActiveStatus(active);
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

    @RequestMapping(value = "/toggle/{tagID}", method = RequestMethod.PUT)
    public @ResponseBody Integer toggleTag(@PathVariable(value = "tagID") Integer tagID){

        return tagService.toggleTag(tagID);
    }

    @RequestMapping(value = "/edit/{tagID}", method = RequestMethod.PUT)
    public @ResponseBody Tag editTag(@RequestBody Tag tag, @PathVariable(value = "tagID") Integer tagID){

        return tagService.editTag(tag, tagID);
    }

    @RequestMapping(value = "/subs/{userID}", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Optional<Tag>> getTagsBySub(@PathVariable(value = "userID") Integer userID){

        return tagService.getTagsBySub(userID);
    }
}
