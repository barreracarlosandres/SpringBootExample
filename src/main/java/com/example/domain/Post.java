package com.example.domain;

import com.example.common.infrastructure.exception.PostMessageExeptions;
import com.example.common.domain.validations.Validations;
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

        Validations.valueGreaterThanZero(idPost, PostMessageExeptions.ID_POST_DEBE_SER_MAYOR_QUE_0);
        Validations.notNull(title, PostMessageExeptions.EL_TITULO_NO_PUEDE_SER_NULO);
        Validations.notNull(body, PostMessageExeptions.EL_CUERPO_DEL_POST_NO_PUEDE_SER_NULO);

        this.idPost = idPost;
        this.title = title;
        this.body = body;
    }
}

