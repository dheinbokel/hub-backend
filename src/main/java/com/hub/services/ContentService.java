package com.hub.services;

import com.hub.RequestModels.ContentTagRequest;
import com.hub.daos.*;
import com.hub.exceptions.FileStorageException;
import com.hub.exceptions.HubFileNotFoundException;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.*;
import com.hub.property.FileStorageProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

/**
 * This class's purpose is to perform the logic behind the requests made to the content controller's endpoints.
 * This class was created by Doug Heinbokel on 2/28/19
 */
@Service
public class ContentService {

    private ContentRepository contentRepository;
    private LikeRepository likeRepository;
    private ContentTagRepository contentTagRepository;
    private NotificationRepository notificationRepository;
    private SubscriptionRepository subscriptionRepository;
    private final Path fileStorageLocation;

    /**
     * Constructor for the class.
     * @param contentRepository
     * @param likeRepository
     * @param fileStorageProperties
     */
    ContentService(ContentRepository contentRepository, LikeRepository likeRepository, FileStorageProperties fileStorageProperties,
                   ContentTagRepository contentTagRepository, NotificationRepository notificationRepository, SubscriptionRepository subscriptionRepository){
        this.contentRepository = contentRepository;
        this.likeRepository = likeRepository;
        this.contentTagRepository = contentTagRepository;
        this.notificationRepository = notificationRepository;
        this.subscriptionRepository = subscriptionRepository;

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

    }

    public ArrayList<ContentTag> getContentTagByAllTags(ContentTagRequest contentTagRequest){

        Integer[] tagArray = contentTagRequest.getTagIdArray();
        Set<Integer> usedIDs = new HashSet<>();
        ArrayList<ContentTag> contentTags = new ArrayList<>();

        for(Integer tag : tagArray){

            Iterable<ContentTag> contentTags1 = contentTagRepository.findByTagID(tag);

            for(ContentTag contentTag : contentTags1){

                if(usedIDs.contains(contentTag.getContentID())) {
                    //Do Nothing
                }
                else{
                    usedIDs.add(contentTag.getContentID());
                    contentTags.add(contentTag);
                }
            }
        }
        contentTags.sort(Comparator.comparing(a -> a.getContentID()));
        Collections.reverse(contentTags);
        return contentTags;
    }

    public void sendNotifications(Integer contentID, String contentName, Integer[] tagArray){

        Set<Integer> subs = new HashSet<>();

        Integer[] tags = tagArray;

        for(Integer tag: tags){

            Iterable<Subscription> subscriptions = subscriptionRepository.findByTagID(tag);

            for(Subscription subscription: subscriptions){

                subs.add(subscription.getUserID());
            }
        }

        for(Integer id: subs){

            Notification notification = new Notification(contentName, contentID, id);
            notificationRepository.save(notification);
        }
    }

    public Iterable<ContentTag> getContentTagsByContentID(Integer contentID){

        return contentTagRepository.findByContentID(contentID);
    }

    public Iterable<ContentTag> getContentTagsByTagID(Integer tagID){

        return contentTagRepository.findByTagID(tagID);
    }

    public Integer toggleContent(Integer contentID){

        Content content = contentRepository.findById(contentID)
                .orElseThrow(() -> new HubNotFoundException("Could not find content for contentID: " + contentID));

        if(content.isActive()){
            content.setActive(false);
        }
        else{
            content.setActive(true);
        }

        contentRepository.save(content);
        return contentID;
    }

    public Content editContent(Integer contentID, MultipartFile file, String contentName, String contentType, String tagArray){

        Content content = contentRepository.findById(contentID)
                .orElseThrow(() -> new HubNotFoundException("Could not find content for contentID: " + contentID));

        String fileName = storeFile(file);

        String[] inputArray = tagArray.split(",");

        Integer[] numbers = new Integer[inputArray.length];

        for(int i = 0;i < inputArray.length;i++)
        {

            numbers[i] = Integer.parseInt(inputArray[i]);
        }

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        content.setFileName(fileName);
        content.setFileDownloadUri(fileDownloadUri);
        content.setSize(file.getSize());
        content.setContentName(contentName);
        content.setContentType(contentType);

        deleteContentTagsByID(contentID);

        Content updatedContent = contentRepository.save(content);
        addTagToContent(content.getContentID(), numbers);
        sendNotifications(content.getContentID(), content.getContentName(), numbers);

        return updatedContent;
    }

    public void deleteContentTagsByID(Integer contentID){

        Iterable<ContentTag> contentTags = contentTagRepository.findByContentID(contentID);

        for(ContentTag contentTag : contentTags){

            contentTagRepository.deleteById(contentTag.getContentTagID());
        }
    }
}
