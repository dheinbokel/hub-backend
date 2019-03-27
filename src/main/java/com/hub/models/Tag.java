package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a tag that can be applied to a piece of content. The Entity tag lets spring know that it will
 * represent a database entity and the Table tag sets the name of the table to what is in the parameter.
 * This class was created by Doug Heinbokel on 2/2/19.
 */
@Entity
@Table(name = "TAG")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Tag {

    /**
     * These are the fields for this class. The Id and Generated Value tags let spring
     * know that the USER_ID field will be a PK and will be auto incremented in the database and wont need to be sent in
     * on creating of a new user.  The Column tags set the names of the columns in the database to what is in the
     * parameter.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TAG_ID")
    private Integer tagID;

    @Column(name = "TAG_NAME")
    private String tagName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID")
    private List<ContentTag> contentTags = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAG_ID", referencedColumnName = "TAG_ID")
    private List<Subscription> subscriptions = new ArrayList<>();

    /**
     * Simple default constructor for the Tag class.
     */
    Tag(){

    }

    /**
     * Getters and setters for the Tag class.
     */

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
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
    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    @JsonProperty
    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }


}
