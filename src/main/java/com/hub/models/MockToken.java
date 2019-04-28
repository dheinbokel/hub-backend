package com.hub.models;

/**
 * This class represents a fake token from Active Directory.  The fields represent what will be
 * returned in the JSON web token connection.
 * @author Doug Heinbokel on 1/14/19
 */
public class MockToken {

    private String userName;
    private String firstName;
    private String lastName;
    private String department;
    private String role;

    /**
     * In this class, these fields will represent what we will be looking for with the Active Directory JSON
     * token.  Each field will be initialized to what is being passed in to the constructor.
     * @param userName
     * @param firstName
     * @param lastName
     * @param department
     * @param role
     */
    public MockToken(String userName, String firstName, String lastName, String department, String role) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.role = role;
    }

    /**
     * @return userName
     * Getter for the userName field.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for the userName field.
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for the firstName field.
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the firstName field.
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the lastName field.
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the lastName field.
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the department field.
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Setter for the department field.
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Getter for the role field.
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Setter for the role field.
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}
