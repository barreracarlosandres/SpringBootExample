package com.example.demo.domain;

import com.example.demo.common.domain.validations.Validations;
import lombok.Getter;

@Getter
public class Post {
    private int idPost;
    private String title;
    private String body;

    /**
     * Constructor that represent a Post Object
     *
     * @param idPost identifier of Post. It must be unique, I don't implement tha restriction it would be a good challenge for you
     * @param title  the title of Post
     * @param body   the text of Post
     */
    public Post(int idPost, String title, String body) {

        Validations.valueGreaterThanZero(idPost, "idPost debe ser mayor que 0");
        Validations.notNull(title, "El titulo no puede ser nulo");
        Validations.notNull(body, "El cuerpo del post no puede ser nulo");

        this.idPost = idPost;
        this.title = title;
        this.body = body;
    }
}

