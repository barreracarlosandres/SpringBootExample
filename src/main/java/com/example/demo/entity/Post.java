package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Post {
    int postId;
    String title;
    String body;

    /**
     * Constructor that represent a Post Object
     *
     * @param postId identifier of Post. It must be unique, I don't implement tha restriction it would be a good challenge for you
     * @param title the title of Post
     * @param body the text of Post
     */
    public Post(int postId, String title, String body) {
        this.postId = postId;
        this.title = title;
        this.body = body;
    }
}

