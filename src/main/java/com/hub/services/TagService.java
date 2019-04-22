package com.hub.services;

import com.hub.daos.ContentTagRepository;
import com.hub.daos.SubscriptionRepository;
import com.hub.daos.TagRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.ContentTag;
import com.hub.models.Subscription;
import com.hub.models.Tag;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TagService {

    private TagRepository tagRepository;
    private SubscriptionRepository subscriptionRepository;
    private ContentTagRepository contentTagRepository;

    TagService(TagRepository tagRepository, SubscriptionRepository subscriptionRepository, ContentTagRepository contentTagRepository){

        this.subscriptionRepository = subscriptionRepository;
        this.tagRepository = tagRepository;
        this.contentTagRepository = contentTagRepository;
    }

    public Iterable<Tag> findAllTagsBYActiveStatus(boolean isActive){

        return tagRepository.findByIsActive(isActive);
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

    public Integer toggleTag(Integer tagID){

        Tag tag = tagRepository.findById(tagID)
                .orElseThrow(() -> new HubNotFoundException("Could not find tag for tagID: " + tagID));

        if(tag.isActive()){
            tag.setActive(false);
            Iterable<Subscription> subscriptions = subscriptionRepository.findByTagID(tagID);
            Iterable<ContentTag> contentTags = contentTagRepository.findByTagID(tagID);

            for(Subscription subscription : subscriptions){

                subscriptionRepository.deleteById(subscription.getSubID());
            }
            for(ContentTag contentTag : contentTags){

                contentTagRepository.deleteById(contentTag.getContentTagID());
            }
        }
        else{
            tag.setActive(true);
        }

        tagRepository.save(tag);
        return tagID;
    }

    public Tag editTag(Tag tag, Integer tagID){

        Tag newTag = tagRepository.findById(tagID)
                .orElseThrow(() -> new HubNotFoundException("Could not find tag for tagID: " + tagID));

        newTag.setTagName(tag.getTagName());

        Tag updatedTag = tagRepository.save(newTag);
        return updatedTag;
    }

    public ArrayList<Optional<Tag>> getTagsBySub(Integer userID){

        Iterable<Subscription> subscriptions = subscriptionRepository.findByUserID(userID);

        ArrayList<Optional<Tag>> optionals = new ArrayList<>();
        for (Subscription sub : subscriptions){

            Optional<Tag> tag = tagRepository.findById(sub.getTagID());

            if(tag.isPresent()){

                optionals.add(tag);
            }
        }
        return optionals;
    }
}
