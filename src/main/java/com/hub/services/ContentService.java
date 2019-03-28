package com.hub.services;

import com.hub.daos.ContentRepository;
import com.hub.daos.ContentTagRepository;
import com.hub.daos.LikeRepository;
import com.hub.exceptions.FileStorageException;
import com.hub.exceptions.HubFileNotFoundException;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.Content;
import com.hub.models.ContentTag;
import com.hub.models.Like;
import com.hub.property.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * This class's purpose is to perform the logic behind the requests made to the content controller's endpoints.
 * This class was created by Doug Heinbokel on 2/28/19
 */
@Service
public class ContentService {

    private ContentRepository contentRepository;
    private LikeRepository likeRepository;
    private ContentTagRepository contentTagRepository;
    private final Path fileStorageLocation;

    /**
     * Constructor for the class.
     * @param contentRepository
     * @param likeRepository
     * @param fileStorageProperties
     */
    ContentService(ContentRepository contentRepository, LikeRepository likeRepository, FileStorageProperties fileStorageProperties,
                   ContentTagRepository contentTagRepository){
        this.contentRepository = contentRepository;
        this.likeRepository = likeRepository;
        this.contentTagRepository = contentTagRepository;

        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
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
        throw new HubNotFoundException("Could not find content for contentID: " + contentID);
    }

    /**
     * Returns all content with the content type that matches what is passed into the parameters.
     * @param contentType
     * @return
     */
    public Iterable<Content> findByContentType(String contentType){

        return contentRepository.findByContentType(contentType);
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

    /**
     * Stores the file coming in to the directory saved in the fileStorageLocation up above.  Checks first to see if there
     * is a correct directory sequence, then adds the file to the directory.  If there is already a file with the same name,
     * it is overridden.  Then, the fileName is returned.
     * @param file
     * @return
     */
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new HubFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new HubFileNotFoundException("File not found " + fileName, ex);
        }
    }

    /**
     * Adds a record in the like table with an associated userID and contentID.  The count of records associated with a
     * particular piece of content can be used to check the amount of likes a piece of content has.
     * @param userID
     * @param contentID
     */
    public void likeContent(Integer userID, Integer contentID){

        String user;

        if(userID >= 10){
            user = userID.toString();
        }
        else{
            user = "0" + userID.toString();
        }

        String content;

        if(contentID >= 10) {
            content = contentID.toString();
        }
        else{
            content = "0" + contentID.toString();
        }
        String likeID = user + content;

        Like newLike = new Like(likeID, userID, contentID);
        likeRepository.save(newLike);

    }

    public void addTagToContent(Integer contentID, Integer[] tagArray){

        String content;

        if(contentID >= 10){

            content = contentID.toString();
        }
        else{

            content = "0" + contentID.toString();
        }

        Integer[] contentTags = tagArray;

        String tag;

        for(Integer contentTag : contentTags){

            if(contentTag >= 10){

                tag = contentTag.toString();
            }
            else{

                tag = "0" + contentTag.toString();
            }

            String contentTagID = content + tag;

            ContentTag contTag = new ContentTag(contentTagID, contentID, contentTag);
            contentTagRepository.save(contTag);
        }


//        String content;
//
//        if(contentID >= 10){
//            content = contentID.toString();
//        }
//        else{
//            content = "0" + contentID.toString();
//        }
//
//        String tag;
//
//        if(tagID >= 10) {
//            tag = tagID.toString();
//        }
//        else{
//            tag = "0" + tagID.toString();
//        }
//        String contentTagID = content + tag;
//
//        ContentTag contentTag = new ContentTag(contentTagID, contentID, tagID);
//        contentTagRepository.save(contentTag);
    }

    public Iterable<ContentTag> getContentTagsByContentID(Integer contentID){

        return contentTagRepository.findByContentID(contentID);
    }

    public Iterable<ContentTag> getContentTagsByTagID(Integer tagID){

        return contentTagRepository.findByTagID(tagID);
    }

}
