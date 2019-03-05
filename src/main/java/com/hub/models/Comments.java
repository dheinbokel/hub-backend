package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Integer commentID;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "CONTENT_ID")
    private Integer contentID;

    @Column(name = "USER_ID")
    private Integer userID;

    Comments(){

    }

    /**
     * Getters and setters for the comments class.
     */

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
