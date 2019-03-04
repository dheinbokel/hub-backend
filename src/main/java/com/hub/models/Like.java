package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "CONTENT_LIKES")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Like {

    @Id
    @Column(name = "LIKE_ID")
    private String likeID;
    @Column(name = "USER_ID")
    private Integer userID;
    @Column(name = "CONTENT_ID")
    private Integer contentID;

    public Like(){

    }

    public Like(String likeID, Integer userID, Integer contentID) {
        this.likeID = likeID;
        this.userID = userID;
        this.contentID = contentID;
    }

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
