package com.hub.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This class acts as a way to keep track of the file directory of content being posted to and from the back end.  When
 * an endpoint is called with a file, this class is used to start keeping track of the directory.
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
}