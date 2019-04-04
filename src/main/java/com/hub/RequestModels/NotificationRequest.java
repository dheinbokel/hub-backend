package com.hub.RequestModels;

import javax.validation.constraints.NotNull;

public class NotificationRequest {

    @NotNull
    private Integer[] notificationIDs;

    public NotificationRequest() {

    }

    public NotificationRequest(Integer[] notificationIDs) {
        this.notificationIDs = notificationIDs;
    }

    public Integer[] getNotificationIDs() {
        return notificationIDs;
    }

    public void setNotificationIDs(Integer[] notificationIDs) {
        this.notificationIDs = notificationIDs;
    }
}
