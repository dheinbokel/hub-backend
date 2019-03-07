package com.hub.services;

import com.hub.daos.TagRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.Tag;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    private TagRepository tagRepository;

    TagService(TagRepository tagRepository){

        this.tagRepository = tagRepository;
    }

    public Iterable<Tag> findAllTags(){

        return tagRepository.findAll();
    }

    public Optional<Tag> findTagById(Integer tagID){

        Optional<Tag> tag = tagRepository.findById(tagID);

        if(tag.isPresent()){

            return tag;
        }

        throw new HubNotFoundException("Could not find tag for tagID: " + tagID);
    }

    public Tag addTag(Tag tag){

        tagRepository.save(tag);

        return tag;
    }
}
