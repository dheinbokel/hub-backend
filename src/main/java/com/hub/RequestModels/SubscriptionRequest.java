package com.hub.RequestModels;

import javax.validation.constraints.NotNull;

public class SubscriptionRequest {

    @NotNull
    private Integer userID;

    @NotNull
    private Integer[] tags;

    public SubscriptionRequest() {

    }

    public SubscriptionRequest(Integer userID, Integer[] tags) {
        this.userID = userID;
        this.tags = tags;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer[] getTags() {
        return tags;
    }

    public void setTags(Integer[] tags) {
        this.tags = tags;
    }
}
