package com.hub.services;

import com.hub.daos.ContentRepository;
import com.hub.daos.LikeRepository;
import com.hub.exceptions.FileStorageException;
import com.hub.exceptions.HubFileNotFoundException;
import com.hub.exceptions.HubNotFoundException;
import com.hub.models.Content;
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

@Service
public class ContentService {

    private ContentRepository contentRepository;
    private LikeRepository likeRepository;
    private final Path fileStorageLocation;

    ContentService(ContentRepository contentRepository, LikeRepository likeRepository, FileStorageProperties fileStorageProperties){
        this.contentRepository = contentRepository;
        this.likeRepository = likeRepository;

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

    public void likeContent(Integer userID, Integer contentID){

        String user = userID.toString();
        String content = contentID.toString();

        String likeID = user + content;

        Like newLike = new Like(likeID, userID, contentID);
        likeRepository.save(newLike);

    }

}
