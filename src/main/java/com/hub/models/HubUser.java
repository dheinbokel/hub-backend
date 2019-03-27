package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the user of the Hub.  After logging in, this information will be sent to the front end to keep
 * track of who the user is.  The Entity tag lets spring know that it will represent a database entity and the Table tag
 * sets the name of the table to what is in the parameter.
 * This class was created by Doug Heinbokel on 2/1/19.
 */
@Entity
@Table(name = "HUB_USER")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HubUser {

    /**
     * The fields for this class represent the attributes kept on the user.  The Id and Generated Value tags let spring
     * know that the USER_ID field will be a PK and will be auto incremented in the database and wont need to be sent in
     * on creating of a new user.  The Column tags set the names of the columns in the database to what is in the
     * parameter. dptID, frID, and prmID are left as Integers as they are FK's in the table that link to the PK's in the
     * parameters.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Integer userID;

    @NotNull
    @Column(name = "USERNAME", unique = true)
    private String userName;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String fName;

    @NotNull
    @Column(name = "LAST_NAME")
    private String lName;

    @Column(name = "DEPARTMENT_ID")
    private Integer dptID;

    @Column(name = "FRANCHISE_ID")
    private Integer frID;

    @Column(name = "PERMISSIONS_ID")
    private Integer prmID;

    @OneToMany
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private List<Comments> userComments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private List<Subscription> subscriptions = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private List<Like> likes = new ArrayList<>();

    /**
     * Simple default constructor for the class.
     */
    HubUser(){

    }

    /**
     * Getters and setters for the HubUser class.
     */
    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getDptID() {
        return dptID;
    }

    public void setDptID(Integer dptID) {
        this.dptID = dptID;
    }

    public Integer getFrID() {
        return frID;
    }

    public void setFrID(Integer frID) {
        this.frID = frID;
    }

    public Integer getPrmID() {
        return prmID;
    }

    public void setPrmID(Integer prmID) {
        this.prmID = prmID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @JsonIgnore
    public List<Comments> getUserComments() {
        return userComments;
    }

    @JsonProperty
    public void setUserComments(List<Comments> userComments) {
        this.userComments = userComments;
    }

    @JsonIgnore
    public List<Like> getLikes() {
        return likes;
    }

    @JsonProperty
    public void setLikes(List<Like> likes) {
        this.likes = likes;
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
