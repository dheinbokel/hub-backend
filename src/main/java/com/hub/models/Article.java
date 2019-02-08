package com.hub.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents the articles that may be posted by the admins in the hub.  Each article may have a list of both
 * comments and contents as shown below in the last 2 fields.  The article class acts as a frame in which its contents
 * are put.  Each piece of content is part of the lists and each comment is as well.
 * This class was created by Doug Heinbokel on 2/4/19
 */
@Entity
@Table(name = "ARTICLES")
public class Article {

    /**
     * These are the fields of the article class and the last 2 fields are the lists that contain the comments and contents
     * for the article, each using the article field "ARTICLE_ID" as a FK in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ARTICLE_ID")
    private Integer articleID;
    @Column(name = "ARTICLE_NAME")
    private String articleName;
    @Column(name = "DATE_CREATED")
    private String createDate;
    @Column(name = "ACTIVE")
    private boolean isActive;
    @OneToMany
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID")
    private List<Content> articleContent = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ARTICLE_ID")
    private List<Comments> articleComments = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "ARTICLE_TAGS",
            joinColumns = {@JoinColumn(name = "ARTICLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "TAG_ID")})
    private Set<Tag> tags = new HashSet<>();

    /**
     * Simple default constructor for the article class
     */
    Article(){

    }

    /**
     * Getters and setters for the article class
     */
    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Content> getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(List<Content> articleContent) {
        this.articleContent = articleContent;
    }

    public List<Comments> getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(List<Comments> articleComments) {
        this.articleComments = articleComments;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
