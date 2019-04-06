package com.hub.RequestModels;

import javax.validation.constraints.NotNull;

public class ContentTagRequest {

    @NotNull
    private Integer[] tagIdArray;

    public ContentTagRequest() {

    }

    public ContentTagRequest(@NotNull Integer[] tagIdArray) {
        this.tagIdArray = tagIdArray;
    }

    public Integer[] getTagIdArray() {
        return tagIdArray;
    }

    public void setTagIdArray(Integer[] tagIdArray) {
        this.tagIdArray = tagIdArray;
    }
}
