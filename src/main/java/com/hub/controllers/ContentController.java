package com.hub.controllers;

import com.hub.models.Content;
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
     * This endpoint method is used to return a list of every piece of content from the
     * database.  It sends back the entire list as JSON.
     * @return Iterable<Content>
     */
    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public @ResponseBody Iterable<Content> getAllContent(){
        return contentService.findAllContent();
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

    /**
     * End point is called when the user would like to add content to the database.  Requests
     * fields to be sent in and then the object is saved to the database.
     */
    @RequestMapping(value = "/content/add", method = RequestMethod.POST)
    public @ResponseBody Content addContent(@RequestParam("file") MultipartFile file, @RequestParam String contentName,
                        @RequestParam String contentType){

        String fileName = contentService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        Calendar cal = Calendar.getInstance();
        String dateTime = sdf.format(cal.getTime());

        Content content = new Content();
        content.setFileName(fileName);
        content.setFileDownloadUri(fileDownloadUri);
        content.setSize(file.getSize());
        content.setContentName(contentName);
        content.setContentType(contentType);
        content.setCreateDate(dateTime);
        content.setActive(true);

        contentService.addContent(content);

        return content;
    }

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
}
