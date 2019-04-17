/**
 * This class was created by Doug Heinbokel on 1/20/19.
 * This class represents a database entity.  In particular, it represents a piece of content
 * that would be sent to and from the database.  Each piece of content is associated with an article and makes up the
 * body of each article.
 */

package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CONTENT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Content {

    /**
     * These are the fields for the entity.  They mirror the column names in the database
     * for the Content table. The final field is the foreign key that relates to the article tables primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTENT_ID")
    private Integer contentID;

    @NotBlank
    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "CONTENT_LOCATION")
    private String fileDownloadUri;

    @NotBlank
    @Column(name = "CONTENT_NAME")
    private String contentName;

    @NotBlank
    @Column(name = "CONTENT_TYPE")
    private String contentType;

    @Column(name = "CONTENT_SIZE")
    private long size;

    @Column(name = "DATE_CREATED")
    private String createDate;

    @Column(name = "FEATURED")
    private boolean isFeatured;

    @Column(name = "ACTIVE")
    private boolean isActive;

    @OneToMany
    @JoinColumn(name = "CONTENT_ID", referencedColumnName = "CONTENT_ID")
    private List<Comments> contentComments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTENT_ID", referencedColumnName = "CONTENT_ID")
    private List<ContentTag> contentTags = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "CONTENT_ID", referencedColumnName = "CONTENT_ID")
    private List<Like> likes = new ArrayList<>();

    public Content(){

        isFeatured = false;
    }

    /**
     * Getters and Setters for the Content entity.
     */
    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @JsonIgnore
    public boolean isActive() {
        return isActive;
    }

    @JsonProperty
    public void setActive(boolean active) {
        isActive = active;
    }

    @JsonIgnore
    public List<Comments> getContentComments() {
        return contentComments;
    }

    @JsonProperty
    public void setContentComments(List<Comments> contentComments) {
        this.contentComments = contentComments;
    }

    @JsonIgnore
    public List<ContentTag> getContentTags() {
        return contentTags;
    }

    @JsonProperty
    public void setContentTags(List<ContentTag> contentTags) {
        this.contentTags = contentTags;
    }

    @JsonIgnore
    public List<Like> getLikes() {
        return likes;
    }

    @JsonProperty
    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType.replaceAll("\\s","");
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName.trim();
    }

    @JsonIgnore
    public boolean isFeatured() {
        return isFeatured;
    }

    @JsonProperty
    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }
}
