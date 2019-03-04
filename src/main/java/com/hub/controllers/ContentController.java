package com.hub.controllers;

import com.hub.models.Content;
import com.hub.models.Like;
import com.hub.services.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This class was created by Doug Heinbokel on 1/20/19.  The purpose of this class is to
 * listen for calls from the front end with regard to content creation.  When the front
 * end calls, it will use the /content end point.  After this, the different requests can
 * be called for different user needs.  @Controller lets Spring know that this class
 * is a controller and will listen for HTTP requests.
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    /*
    ContentController(ContentRepository contentRepository){
        this.contentRepository = contentRepository;
    }*/


    /**
     * This endpoint method is used to return a list of every piece of content from the
     * database.  It sends back the entire list as JSON.
     * @return Iterable<Content>
     */
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public @ResponseBody Iterable<Content> getAllContent(){
        return contentService.findAllContent();
    }

    /**
     * End point is called when a user would like to get a particular piece of content by its id.  The user must provide
     * an id that they would like to search by.
     * @param contentID
     * @return
     */
    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    public Optional<Content> getContentById(@PathVariable(value = "id") Integer contentID){
        return contentService.findContentById(contentID);
    }

    /**
     * End point is called when the user would like to add content to the database.  Requests
     * fields to be sent in and then the object is saved to the database.
     */
    @RequestMapping(value = "/content/add", method = RequestMethod.POST)
    public @ResponseBody Content addContent(@RequestBody Content content){

        return contentService.addContent(content);
    }

    @RequestMapping(value = "/content/like/{userID}/{contentID}", method = RequestMethod.POST)
    public @ResponseBody Like likeContent(@PathVariable(value = "userID") Integer userID, @PathVariable(value = "contentID") Integer contentID){

        return contentService.likeContent(userID, contentID);
    }
}
