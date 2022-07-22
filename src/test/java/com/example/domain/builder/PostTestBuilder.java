package com.example.domain.builder;


import com.example.application.fabric.CommandPost;

public class PostTestBuilder {

    //Patter Chain Builder
    int postId = 1;
    String title = "Prueba";
    String body = "Cadillac equity refers attend bother soon can, racial wide hospitality start juice bottom persons, attention announced pills diagnosis passage individuals tunnel. ";

    public PostTestBuilder() {
    }

    public PostTestBuilder withPostId(int postId) {
        this.postId = postId;
        return this;
    }

    public PostTestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PostTestBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public CommandPost build() {
        return new CommandPost(this.postId, this.title, this.body);
    }
}