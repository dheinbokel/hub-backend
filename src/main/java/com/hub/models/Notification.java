package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "NOTIFICATIONS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NOTIFICATION_ID")
    private Integer notificationID;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "CONTENT_ID")
    private Integer contentID;

    @Column(name = "USER_ID")
    private Integer userID;

    @Column(name = "ACTIVE")
    private boolean isActive;

    Notification(){

    }

    public Notification(String contentName, Integer contentID, Integer userID) {

        this.contentID = contentID;
        this.userID = userID;
        this.isActive = true;
        this.message = "New content: " + contentName;
    }

    public Integer getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(Integer notificationID) {
        this.notificationID = notificationID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
    }

    @JsonIgnore
    public Integer getUserID() {
        return userID;
    }

    @JsonProperty
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
