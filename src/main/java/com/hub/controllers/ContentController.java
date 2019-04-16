package com.hub.controllers;

import com.hub.RequestModels.ContentTagRequest;
import com.hub.models.Content;
import com.hub.models.ContentTag;
import com.hub.services.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

/**
 * This class was created by Doug Heinbokel on 1/20/19.  The purpose of this class is to
 * listen for calls from the front end with regard to content creation.  When the front
 * end calls, it will use the /content end point.  After this, the different requests can
 * be called for different user needs.  @Controller lets Spring know that this class
 * is a controller and will listen for HTTP requests.
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
@RestController
public class ContentController {

    private ContentService contentService;
    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);
    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    ContentController(ContentService contentService){

        this.contentService = contentService;
    }

    /**
     * This method returns all content with the same content type as what was passed through the parameters.  It sends
     * it back in the form of an iterable in the response body. Sends iterable in JSON.
     * @param contentType
     * @return
     */
    @RequestMapping(value = "/content/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Content> findByContentType(@RequestParam String contentType,
                                                             @RequestParam(defaultValue = "true", required = false) boolean active){

        return contentService.findByContentType(contentType, active);
    }

    @RequestMapping(value = "content/featured", method = RequestMethod.GET)
    public @ResponseBody Iterable<Content> findAllFeaturedContent(){

        return contentService.findAllFeaturedContent();
    }

    /**
     * End point is called when a user would like to get a particular piece of content by its id.  The user must provide
     * an id that they would like to search by.
     * @param contentID
     * @return
     */
    @RequestMapping(value = "/content/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Content> getContentById(@PathVariable(value = "id") Integer contentID){
        return contentService.findContentById(contentID);
    }

    @RequestMapping(value = "/content/tag/bycontentid/{contentID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<ContentTag> getContentTagByContentID(@PathVariable(value = "contentID") Integer contentID){

        return contentService.getContentTagsByContentID(contentID);
    }

    @RequestMapping(value = "/content/tag/bytagid/{tagID}", method = RequestMethod.GET)
    public @ResponseBody Iterable<ContentTag> getContentTagByTagID(@PathVariable(value = "tagID") Integer tagID){

        return contentService.getContentTagsByTagID(tagID);
    }

    @RequestMapping(value = "/contentTags", method = RequestMethod.POST)
    public @ResponseBody ArrayList<ContentTag> getContentTagsByTagsArray(@RequestBody ContentTagRequest contentTagRequest){

        return contentService.getContentTagByAllTags(contentTagRequest);
    }

    @RequestMapping(value = "/content/toggle/{contentID}", method = RequestMethod.PUT)
    public @ResponseBody Integer toggleContent(@PathVariable(value = "contentID") Integer contentID){

        return contentService.toggleContent(contentID);
    }

    @RequestMapping(value = "/featured/toggle/{contentID}", method = RequestMethod.PUT)
    public @ResponseBody Integer toggleFeaturedContent(@PathVariable(value = "contentID") Integer contentID){

        return contentService.toggleFeaturedContent(contentID);
    }

    /**
     * End point is called when the user would like to add content to the database.  Requests
     * fields to be sent in and then the object is saved to the database.  Requires a file, contentName, and contentType
     * to be sent in with the body of the response as form-data. Sets all of the content fields and submits them to the
     * content service.
     */
    @RequestMapping(value = "/content/add", method = RequestMethod.POST)
    public @ResponseBody Content addContent(@RequestParam("file") MultipartFile file, @RequestParam String contentName,
                        @RequestParam String contentType, @RequestParam String tagArray){

        String fileName = contentService.storeFile(file);

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

        /**
         * Sets the current date and time of the post.
         */
        Calendar cal = Calendar.getInstance();
        String dateTime = sdf.format(cal.getTime());

        /**
         * Setting the fields in the content class.
         */
        Content content = new Content();
        content.setFileName(fileName);
        content.setFileDownloadUri(fileDownloadUri);
        content.setSize(file.getSize());
        content.setContentName(contentName);
        content.setContentType(contentType);
        content.setCreateDate(dateTime);
        content.setActive(true);

        /**
         * Adding the content to the database.
         */
        contentService.addContent(content);

        contentService.addTagToContent(content.getContentID(), contentName, numbers);

        contentService.sendNotifications(content.getContentID(), content.getContentName(), numbers);

        return content;
    }

    @RequestMapping(value = "/content/edit/{contentID}", method = RequestMethod.PUT)
    public @ResponseBody Content editContent(@RequestParam("file") MultipartFile file, @RequestParam String contentName,
                                            @RequestParam String contentType, @RequestParam String tagArray,
                                             @PathVariable(value = "contentID") Integer contentID){

        return contentService.editContent(contentID, file, contentName, contentType, tagArray);
    }

    /**
     * This endpoint sends the directory of the content file to the user to display or download. It requires a file name
     * and a request.
     * @param fileName
     * @param request
     * @return
     */
    @RequestMapping(value = "/downloadFile/{fileName:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = contentService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    /**
     * This endpoint is used when the user likes or attempts to like a piece of content.
     * @param userID
     * @param contentID
     * @return
     */
    @RequestMapping(value = "/content/like/{userID}/{contentID}", method = RequestMethod.POST)
    public void likeContent(@PathVariable(value = "userID") Integer userID, @PathVariable(value = "contentID") Integer contentID){

        contentService.likeContent(userID, contentID);
    }

    @RequestMapping(value = "/content/dislike/{userID}/{contentID}", method = RequestMethod.DELETE)
    public void dislikeContent(@PathVariable(value = "userID") Integer userID, @PathVariable(value = "contentID") Integer contentID){

        contentService.dislikeContent(userID, contentID);
    }

    /**
     * This endpoint sends a count of the amount of likes a piece of content has back to the front end as an int value.
     * @param contentID Integer
     * @return int count of likes for the associated content.
     */
    @RequestMapping(value = "/likes/{contentID}", method = RequestMethod.GET)
    public @ResponseBody int getContentLikes(@PathVariable(value = "contentID") Integer contentID){

        return contentService.countLikesForContent(contentID);
    }

    /**
     * This endpoint will retrieve content based on its active status, being either active or inactive. The defult
     * value is set to true so all content that is currently active will be sent back if nothing is sent as a parameter.
     * @param active
     * @return
     */
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public @ResponseBody Iterable<Content> findActiveContent(@RequestParam(defaultValue = "true", required = false) boolean active){

        return contentService.findByActive(active);
    }
}
