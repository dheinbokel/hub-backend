package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the department that the User will be a part of. The Id and GeneratedValue annotations tell
 * Spring that the dptID is the id for the entity and that the id will be auto generated by the database upon addition
 * to the database.
 * Created by Doug Heinbokel on 1/31/19.
 */
@Entity
@Table(name = "DEPARTMENT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department {

    /**
     * Fields for the Department class. The Id and Generated value annotations let Spring know that the dptID will be
     * auto generated by the database and that we will not have to pass in an ID when creating a new Department.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "DEPARTMENT_ID")
    private Integer dptID;

    @Column(name = "DPT_NAME")
    private String dptName;

    @Column(name = "ACTIVE")
    private boolean isActive;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    private List<HubUser> hubUsers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    private List<HubEvent> hubEvents = new ArrayList<>();

    /**
     * Default constructor, needed for spring to interact with entity.
     */
    Department(){
        isActive = true;
    }

    /**
     * Getters and Setters for the Department class.
     */
    public Integer getDptID() {
        return dptID;
    }

    public void setDptID(Integer dptID) {
        this.dptID = dptID;
    }

    public String getDptName() {
        return dptName;
    }

    public void setDptName(String dptName) {
        this.dptName = dptName;
    }

    @JsonIgnore
    public List<HubUser> getHubUsers() {
        return hubUsers;
    }

    @JsonProperty
    public void setHubUsers(List<HubUser> hubUsers) {
        this.hubUsers = hubUsers;
    }

    @JsonIgnore
    public List<HubEvent> getHubEvents() {
        return hubEvents;
    }

    @JsonProperty
    public void setHubEvents(List<HubEvent> hubEvents) {
        this.hubEvents = hubEvents;
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


