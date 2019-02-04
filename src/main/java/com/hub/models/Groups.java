package com.hub.models;

import javax.persistence.*;

/**
 * This class represents groups.  (come back with a better explanation).
 * The Entity tag lets spring know that it will represent a database entity and the Table tag
 * sets the name of the table to what is in the parameter.
 * This class was created by Doug Heinbokel on 2/2/19
 */
@Entity
@Table(name = "GROUPS")
public class Groups {

    /**
     * These are the fields of the Groups class.  The Id and Generated Value tags let spring
     * know that the USER_ID field will be a PK and will be auto incremented in the database and wont need to be sent in
     * on creating of a new user.  The Column tags set the names of the columns in the database to what is in the
     * parameter.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GROUP_ID")
    private Integer groupID;
    @Column(name = "GROUP_NAME")
    private String groupName;

    /**
     * Simple default constructor for the Groups class.
     */
    Groups(){

    }

    /**
     * Getters and setters for the Groups class.
     */
    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
