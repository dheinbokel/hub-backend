/**
 * This class was created by Doug Heinbokel on 1/20/19.
 * This class represents a database entity.  In particular, it represents a piece of content
 * that would be sent to and from the database.
 */

package com.hub.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Content {

    /**
     * Remember to fix contentType typo in the database and in here and controller
     * These are the fields for the entity.  They mirror the column names in the database
     * for the Content table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer contentID;
    private String content;
    private String contentName;
    private String contentTpye;
    private String createDate;
    private boolean isActive;

    public Content(){
        
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentType() {
        return contentTpye;
    }

    public void setContentType(String contentType) {
        this.contentTpye = contentType;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
