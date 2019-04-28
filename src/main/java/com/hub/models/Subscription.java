package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * This class represents a user's subscription and is used to relay notifications to the user and subscribe the user
 * to tags.
 */
@Entity
@Table(name = "SUBSCRIPTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subscription {

    @Id
    @Column(name = "SUBSCRIPTION_ID")
    private String subID;

    @NotNull
    @Column(name = "USER_ID")
    private Integer userID;

    @NotNull
    @Column(name = "TAG_ID")
    private Integer tagID;

    Subscription(){

    }

    public Subscription(String subID, Integer userID, Integer tagID) {
        this.subID = subID;
        this.userID = userID;
        this.tagID = tagID;
    }

    /**
     * Getters and setters
     */
    public String getSubID() {
        return subID;
    }

    public void setSubID(String subID) {
        this.subID = subID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }
}
