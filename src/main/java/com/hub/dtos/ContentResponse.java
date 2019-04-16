package com.hub.dtos;

public class ContentResponse {

    private Integer contentID;
    private String contentName;

    public ContentResponse() {

    }

    public ContentResponse(Integer contentID, String contentName) {
        this.contentID = contentID;
        this.contentName = contentName;
    }

    public Integer getContentID() {
        return contentID;
    }

    public void setContentID(Integer contentID) {
        this.contentID = contentID;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }
}
