package com.hub.controllers;

import com.hub.daos.HubEventRepository;
import com.hub.models.HubEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/events")
public class HubEventController {

    @Autowired
    private HubEventRepository hubEventRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubEvent> getAllEvents(){
        return hubEventRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody HubEvent getEventById(@PathVariable(value = "id")Integer eventID){
        return hubEventRepository.getOne(eventID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody HubEvent addEvent(@RequestBody HubEvent hubEvent){
        hubEventRepository.save(hubEvent);
        return hubEvent;
    }
}
