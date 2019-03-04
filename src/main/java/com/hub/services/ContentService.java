package com.hub.services;

import com.hub.daos.ContentRepository;
import com.hub.daos.LikeRepository;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.Content;
import com.hub.models.Like;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService {

    private ContentRepository contentRepository;
    private LikeRepository likeRepository;

    ContentService(ContentRepository contentRepository, LikeRepository likeRepository){
        this.contentRepository = contentRepository;
        this.likeRepository = likeRepository;
    }

    /**
     * Finds a piece of content from the database by its ID.
     * @param contentID
     * @return
     */
    public Optional<Content> findContentById(Integer contentID){

        Optional<Content> content = contentRepository.findById(contentID);

        if(content.isPresent()){
            return content;
        }
        throw new HubNotFoundException("Could not find permissions for permission ID: " + contentID);
    }

    /**
     * Gets all the content from the database.
     * @return
     */
    public Iterable<Content> findAllContent(){

        return contentRepository.findAll();
    }

    /**
     * Adds a piece of content to the database.
     * @param content
     * @return
     */
    public Content addContent(Content content){

        contentRepository.save(content);
        return  content;
    }

    public Like likeContent(Integer userID, Integer contentID){

        String user = userID.toString();
        String content = contentID.toString();

        String likeID = user + content;

        Like newLike = new Like(likeID, userID, contentID);
        likeRepository.save(newLike);

        return newLike;
    }

}
