/**
 * This class was created by Doug Heinbokel on 1/20/19.
 * This class represents a database entity.  In particular, it represents a piece of content
 * that would be sent to and from the database.  Each piece of content is associated with an article and makes up the
 * body of each article.
 */

package com.hub.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Column(name = "DATE_CREATED")
    private String createDate;
    @Column(name = "ACTIVE")
    private boolean isActive;
    @OneToMany
    @JoinColumn(name = "CONTENT_ID", referencedColumnName = "CONTENT_ID")
    private List<Comments> contentComments = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ARTICLE_TAGS",
            joinColumns = {@JoinColumn(name = "CONTENT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    private Set<Tag> tags = new HashSet<>();


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

    public List<Comments> getContentComments() {
        return contentComments;
    }

    public void setContentComments(List<Comments> contentComments) {
        this.contentComments = contentComments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
