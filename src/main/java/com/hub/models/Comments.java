package com.hub.models;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTENT_ID")
    private Integer contentID;
    @Column(name = "COMMENT")
    private String comment;
    @Column(name = "CREATE_DATE")
    private String createDate;
    @Column(name = "ARTICLE_ID")
    private Integer articleID;
    @Column(name = "USER_ID")
    private Integer userID;

    Comments(){

    }

    /**
     * Getters and setters for the comments class.
     */
    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
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

    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }
}
