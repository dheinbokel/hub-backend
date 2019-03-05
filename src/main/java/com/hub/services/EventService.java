package com.hub.services;

import com.hub.daos.HubEventRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.HubEvent;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EventService {

    private HubEventRepository hubEventRepository;

    EventService(HubEventRepository hubEventRepository){

        this.hubEventRepository = hubEventRepository;
    }

    public Iterable<HubEvent> findAllEvents(){

        return hubEventRepository.findAll();
    }

    public Optional<HubEvent> findEventById(Integer eventID){

        Optional<HubEvent> event = hubEventRepository.findById(eventID);

        if(event.isPresent()){
            return event;
        }
        throw new HubNotFoundException("Could not find event for eventID: " + eventID);
    }

    public HubEvent addEvent(HubEvent hubEvent){

        hubEventRepository.save(hubEvent);

        return hubEvent;
    }
}
