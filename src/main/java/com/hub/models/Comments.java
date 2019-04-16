package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Table(name = "COMMENTS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comments {

    private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

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

    @Column(name = "FIRST_NAME")
    private String fName;

    @Column(name = "LAST_NAME")
    private String lName;

    @Column(name = "USER_ID")
    private Integer userID;

    @Column(name = "ACTIVE")
    private boolean isActive;

    Comments(){

        Calendar cal = Calendar.getInstance();
        String dateTime = sdf.format(cal.getTime());
        createDate =dateTime;
        isActive = true;
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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @JsonIgnore
    public boolean isActive() {
        return isActive;
    }

    @JsonProperty
    public void setActive(boolean active) {
        isActive = active;
    }
}
