package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the permissions that the user has on the Hub.  They will be either an admin with permission to
 * create items, or they will be a user without those permissions.
 * This class was created by Doug Heinbokel on 1/31/19.
 */
@Entity
@Table(name = "HUB_PERMISSIONS")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class HubPermissions {

    /**
     * These are the fields for the HubPermissions class. The Id and GeneratedValue annotations tell Spring that the prmID
     * is the id for the entity and that the id will be auto generated by the database upon addition to the database.
     * An id will not be required when creating a new permission.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PERMISSIONS_ID")
    private Integer prmID;
    @Column(name = "CREATE_ITEM")
    private boolean createItem;
    @Column(name = "NAME")
    private String prmName;
    @OneToMany
    @JoinColumn(name = "PERMISSIONS_ID", referencedColumnName = "PERMISSIONS_ID")
    private List<HubUser> hubUsers = new ArrayList<>();

    /**
     * Simple default constructor for the class.
     */
    HubPermissions(){

    }

    /**
     * Getters and setters for the HubPermissions class.
     */
    public Integer getPrmID() {
        return prmID;
    }

    public void setPrmID(Integer prmID) {
        this.prmID = prmID;
    }

    public boolean isCreateItem() {
        return createItem;
    }

    public void setCreateItem(boolean createItem) {
        this.createItem = createItem;
    }

    public String getPrmName() {
        return prmName;
    }

    public void setPrmName(String prmName) {
        this.prmName = prmName;
    }

    public List<HubUser> getHubUsers() {
        return hubUsers;
    }

    public void setHubUsers(List<HubUser> hubUsers) {
        this.hubUsers = hubUsers;
    }
}
