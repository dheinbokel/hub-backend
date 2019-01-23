package com.hub.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;
    private String text;
    private String title;
    private String file;
    private String author;

    public Post(){
        
    }
    /*
    public Post(String text, String title, String file, String author){

        this.text = text;
        this.title = title;
        this.file = file;
        this.author = author;
    }*/

    /*
    @Override
    public String toString(){
        return String.format(
                "Post[id=%d, text='%s', title='%s', file='%s', author='%s']",
                postId, text, title, file, author
        );

    }*/

    /**
     * Getter for the postId
     * @return
     */
    public Integer getId() {
        return postId;
    }

    public void setId(Integer postId) {
        this.postId = postId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
