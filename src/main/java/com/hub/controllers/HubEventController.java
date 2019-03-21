package com.hub.controllers;

import com.hub.models.HubEvent;
import com.hub.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * This class was created by Doug Heinbokel on 3/3/19.
 * This class listens and responds to calls from a front end based on the endpoints and request methods and returns the
 * requested information.
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
@RequestMapping(value = "/events")
public class HubEventController {

    private EventService eventService;

    HubEventController(EventService eventService){

        this.eventService = eventService;
    }

    /**
     * Returns all hub events in the database.
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubEvent> getAllEvents(){
        return eventService.findAllEvents();
    }

    /**
     * Returns the hub event with the same eventID as the id passed in with the path variable.
     * @param eventID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Optional<HubEvent> getEventById(@PathVariable(value = "id")Integer eventID){
        return eventService.findEventById(eventID);
    }

    /**
     * Adds an event to the database and takes in a hubevent object.  Sends back the event to the user after it has been
     * added to the database.
     * @param hubEvent
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody HubEvent addEvent(@RequestBody HubEvent hubEvent){
        eventService.addEvent(hubEvent);
        return hubEvent;
    }

    /**
     * Returns all hub events with matching dptID to the path variable coming in.
     * @param dptID
     * @return
     */
    @RequestMapping(value = "/department/{dptID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubEvent> getEventByDptId(@PathVariable(value = "dptID")Integer dptID){
        return eventService.findEventByDptID(dptID);
    }

    /**
     * Returns all hub events with matching frID to the path variable coming in.
     * @param frID
     * @return
     */
    @RequestMapping(value = "/franchise/{frID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<HubEvent> getEventByFrId(@PathVariable(value = "frID")Integer frID){
        return eventService.findEventByFrID(frID);
    }
}
