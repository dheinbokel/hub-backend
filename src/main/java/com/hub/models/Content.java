/**
 * This class was created by Doug Heinbokel on 1/20/19.
 * This class represents a database entity.  In particular, it represents a piece of content
 * that would be sent to and from the database.  Each piece of content is associated with an article and makes up the
 * body of each article.
 */

package com.hub.models;

import javax.persistence.*;

@Entity
@Table(name = "CONTENT")
public class Content {

    /**
     * These are the fields for the entity.  They mirror the column names in the database
     * for the Content table.  The final field is the foreign key that relates to the article tables primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTENT_ID")
    private Integer contentID;
    @Column(name = "CONTENT_LOCATION")
    private String contentLocation;
    @Column(name = "CONTENT_TYPE")
    private String contentType;
    @Column(name = "ARTICLE_POS")
    private String articlePosition;
    @Column(name = "ARTICLE_ID")
    private Integer articleID;

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

    public String getContentLocation() {
        return contentLocation;
    }

    public void setContentLocation(String contentLocation) {
        this.contentLocation = contentLocation;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getArticlePosition() {
        return articlePosition;
    }

    public void setArticlePosition(String articlePosition) {
        this.articlePosition = articlePosition;
    }

    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }
}
