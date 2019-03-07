package com.hub.controllers;

import com.hub.models.HubEvent;
import com.hub.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/events")
public class HubEventController {

    private EventService eventService;

    HubEventController(EventService eventService){

        this.eventService = eventService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubEvent> getAllEvents(){
        return eventService.findAllEvents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<HubEvent> getEventById(@PathVariable(value = "id")Integer eventID){
        return eventService.findEventById(eventID);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody HubEvent addEvent(@RequestBody HubEvent hubEvent){
        eventService.addEvent(hubEvent);
        return hubEvent;
    }
}
