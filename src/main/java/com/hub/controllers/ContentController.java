package com.hub.controllers;

import com.hub.daos.ContentRepository;
import com.hub.models.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This class was created by Doug Heinbokel on 1/20/19.  The purpose of this class is to
 * listen for calls from the front end with regard to content creation.  When the front
 * end calls, it will use the /content end point.  After this, the different requests can
 * be called for different user needs.  @Controller lets Spring know that this class
 * is a controller and will listen for HTTP requests.
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/content")
public class ContentController {

    @Autowired
    private ContentRepository contentRepository;

    /*
    ContentController(ContentRepository contentRepository){
        this.contentRepository = contentRepository;
    }*/


    /**
     * This endpoint method is used to return a list of every piece of content from the
     * database.  It sends back the entire list as JSON.
     * @return Iterable<Content>
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Content> getAllContent(){
        return contentRepository.findAll();
    }

    /**
     * End point is called when the user would like to add content to the database.  Requests
     * fields to be sent in and then the object is saved to the database.
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public @ResponseBody String addContent(@RequestBody Content content){


        contentRepository.save(content);
        return "Saved";
    }
}
