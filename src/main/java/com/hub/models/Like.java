package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This class represents a like that a piece of content may have.  It holds the userId and contentID of the user logged in
 * and the content being viewed and its id is made up of a combination of both of these fields.
 */
@Entity
@Table(name = "CONTENT_LIKES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Like {

    /**
     * THe fields of the class.
     */
    @Id
    @Column(name = "LIKE_ID")
    private String likeID;

    @NotNull
    @Column(name = "USER_ID")
    private Integer userID;

    @NotNull
    @Column(name = "CONTENT_ID")
    private Integer contentID;

    /**
     * Default constructor of the class.
     */
    public Like(){

    }

    /**
     * Constructor used when all pieces of the class are known.
     * @param likeID
     * @param userID
     * @param contentID
     */
    public Like(String likeID, Integer userID, Integer contentID) {
        this.likeID = likeID;
        this.userID = userID;
        this.contentID = contentID;
    }

    /**
     *Getters and setters for the class.
     */
    public String getLikeID() {
        return likeID;
    }

    public void setLikeID(String likeID) {
        this.likeID = likeID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
    }
}
