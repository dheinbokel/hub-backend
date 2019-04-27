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

    /**
     * This endpoint returns a tag based on the ID passed in to the path variable
     * @param tagID Integer ID of the tag being returned
     * @return Optional Tag
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Tag> getTagById(@PathVariable(value = "id") Integer tagID){
        return tagService.findTagById(tagID);
    }

    /**
     * This endpoint adds a new Tag to the database
     * @param tag Tag object being added
     * @return Tag just created
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody Tag addTag(@RequestBody Tag tag){
        tagService.addTag(tag);
        return tag;
    }

    /**
     * This endpoint toggles the active status of a tag from true to false and back
     * @param tagID Integer ID of the tag being toggled
     * @return Integer ID of the tag being toggled.
     */
    @RequestMapping(value = "/toggle/{tagID}", method = RequestMethod.PUT)
    public @ResponseBody Integer toggleTag(@PathVariable(value = "tagID") Integer tagID){

        return tagService.toggleTag(tagID);
    }

    /**
     * This endpoint is used to edit a Tag with the same ID as the ID given in the path variable
     * @param tag Tag object
     * @param tagID Integer ID of tag being edited
     * @return The tag being edited
     */
    @RequestMapping(value = "/edit/{tagID}", method = RequestMethod.PUT)
    public @ResponseBody Tag editTag(@RequestBody Tag tag, @PathVariable(value = "tagID") Integer tagID){

        return tagService.editTag(tag, tagID);
    }

    /**
     * This endpoint returns an ArrayList of Tags based on the tagIDs of the subscriptions of the user with the same
     * userID as what is passed in the path variable
     * @param userID Integer ID of the user
     * @return ArrayList of Optional Tags
     */
    @RequestMapping(value = "/subs/{userID}", method = RequestMethod.GET)
    public @ResponseBody ArrayList<Optional<Tag>> getTagsBySub(@PathVariable(value = "userID") Integer userID){

        return tagService.getTagsBySub(userID);
    }
}
