package com.hub.services;

import com.hub.daos.HubEventRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.HubEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This class was created by Doug Heinbokel on 3/3/19.
 * This class handles the event related logic and thinking for the calls made to the event controller.
 */
@Service
public class EventService {

    private HubEventRepository hubEventRepository;

    EventService(HubEventRepository hubEventRepository){

        this.hubEventRepository = hubEventRepository;
    }

    /**
     * Returns an iterable of hubevents in the database.
     * @return
     */
    public Iterable<HubEvent> findAllEvents(){

        return hubEventRepository.findAll();
    }

    /**
     * Returns a hubevent with the matching id of what is sent in the parameter.  If null, an exception is thrown.
     * @param eventID
     * @return
     */
    public Optional<HubEvent> findEventById(Integer eventID){

        Optional<HubEvent> event = hubEventRepository.findById(eventID);

        if(event.isPresent()){
            return event;
        }
        throw new HubNotFoundException("Could not find event for eventID: " + eventID);
    }

    /**
     * Adds an event to the database that was passed into the parameter then returns the event back to the user.
     * @param hubEvent
     * @return
     */
    public HubEvent addEvent(HubEvent hubEvent){

        hubEventRepository.save(hubEvent);

        return hubEvent;
    }

    /**
     * Returns all hub events with a department id matching the id passed into the parameter as an iterable.
     * @param dptID
     * @return
     */
    public Iterable<HubEvent> findEventByDptID(Integer dptID){

        return hubEventRepository.findByDptID(dptID);
    }

    /**
     * Returns all hub events with a franchise id matching the id passed into the parameter as an iterable.
     * @param frID
     * @return
     */
    public Iterable<HubEvent> findEventByFrID(Integer frID){

        return hubEventRepository.findByFrID(frID);
    }
}
