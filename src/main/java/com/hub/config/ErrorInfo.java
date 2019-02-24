package com.hub.config;

/**
 * This class is a model for custom error messages being sent to the front-end.  The purpose is to prevent too much info
 * from being shared with the front-end that Spring usually sends by default.
 * This class was created by Doug Heinbokel on 2/23/19
 */
public class ErrorInfo {

    private int errorStatus;
    private String errorMessage;
    private String errors;

    /**
     * Constructor for the class.
     */
    public ErrorInfo() {

    }

    public ErrorInfo(int errorStatus, String errorMessage, String errors) {
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    /**
     * Getters and setters for the class.
     */
    public int getErrorStatus() {
        return errorStatus;
    }

    public void setErrorStatus(int errorStatus) {
        this.errorStatus = errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
