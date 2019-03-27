package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSCRIPTION")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Subscription {

    @Id
    @Column(name = "SUBSCRIPTION_ID")
    private String subID;

    @Column(name = "USER_ID")
    private Integer userID;

    @Column(name = "TAG_ID")
    private Integer tagID;

    Subscription(){

    }

    public Subscription(String subID, Integer userID, Integer tagID) {
        this.subID = subID;
        this.userID = userID;
        this.tagID = tagID;
    }

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
