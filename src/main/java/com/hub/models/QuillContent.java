package com.hub.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "QUILL_CONTENT")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class QuillContent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONTENT_ID")
    private Integer quillContentID;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CONTENT_NAME")
    private String contentName;

    @Column(name = "CONTENT_TYPE")
    private String contentType;

    public QuillContent() {

    }

    public Integer getQuillContentID() {
        return quillContentID;
    }

    public void setQuillContentID(Integer quillContentID) {
        this.quillContentID = quillContentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}


