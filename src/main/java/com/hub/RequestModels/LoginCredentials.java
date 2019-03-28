package com.hub.RequestModels;

/**
 * This class represents the data from the login form from the front-end.  The JSON that comes in from the form
 * is used to create an object of this class which is then passed on as a parameter to test against AD.  In the current
 * version of this system, the AD connection has not been made so the credentials are not checked against anything yet.
 * @author Doug Heinbokel on 1/14/19
 */
public class LoginCredentials {

    private String userName;
    private String password;

    /**
     * Simple constructor for the LoginCredentials class.
     * @param userName
     * @param password
     */
    public LoginCredentials(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
     * You'll need an empty constructor to allow for Jackson to perform it's deserialization actions correctly.
     * This is for when it is being used as a parameter with "request body", such as in the login method in the
     * AccountController class.
     */
    public LoginCredentials(){

    }

    /**
     * Getter for the userName field
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Setter for the userName field
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Getter for the password field
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password field
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
