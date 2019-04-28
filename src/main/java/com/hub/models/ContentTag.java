package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * This class represents an individual tag that is related to a piece of content.
 */
@Entity
@Table(name = "CONTENT_TAG")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ContentTag {

    @Id
    @Column(name = "CONTENT_TAG_ID")
    private String contentTagID;

    @NotBlank
    @Column(name = "RELATED_CONTENT")
    private String relatedContent;

    @NotNull
    @Column(name = "CONTENT_ID")
    private Integer contentID;

    @NotNull
    @Column(name = "TAG_ID")
    private Integer tagID;

    /**
     * Default constructor
     */
    ContentTag(){

    }

    /**
     * Constructor
     * @param contentTagID
     * @param relatedContent
     * @param contentID
     * @param tagID
     */
    public ContentTag(String contentTagID, String relatedContent, Integer contentID, Integer tagID) {
        this.contentTagID = contentTagID;
        this.relatedContent = relatedContent;
        this.contentID = contentID;
        this.tagID = tagID;
    }

    /**
     * Getters and setters for the class.
     */
    public String getContentTagID() {
        return contentTagID;
    }

    public void setContentTagID(String contentTagID) {
        this.contentTagID = contentTagID;
    }

    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }

    public String getRelatedContent() {
        return relatedContent;
    }

    public void setRelatedContent(String relatedContent) {
        this.relatedContent = relatedContent.trim();
    }
}
