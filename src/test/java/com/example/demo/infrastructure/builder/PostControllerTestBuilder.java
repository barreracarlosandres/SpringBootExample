package com.example.demo.infrastructure.builder;


import com.example.demo.application.fabric.CommandPost;

public class PostControllerTestBuilder {

    //Patter Chain Builder
    int postId = 1;
    String title = "Prueba";
    String body = "Cadillac equity refers attend bother soon can, racial wide hospitality start juice bottom persons, attention announced pills diagnosis passage individuals tunnel. ";

    public PostControllerTestBuilder() {
    }

    public PostControllerTestBuilder withPostId(int postId) {
        this.postId = postId;
        return this;
    }

    public PostControllerTestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public PostControllerTestBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public CommandPost build() {
        return new CommandPost(this.postId, this.title, this.body);
    }
}
